package com.cieslak2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {

    public static void main(String[] args) {
        int writerPort = 8004;
        int readerPort = 8003;
        Lib_number book_id=new Lib_number();
        List<Book> bookList=new ArrayList<>();
        List<String> allWritters = new ArrayList<>(); for (int i=0;i<10;i++){allWritters.add("pisarz_"+i);}
        List<String> allReaders = new ArrayList<>(); for (int i=0;i<10;i++){allReaders.add("czytelnik_"+i);}
        List<Writer> writerThreadList = new ArrayList<>();
        List<Reader> readerThreadList = new ArrayList<>();
        try{
            WriterSocketHandler writersockethandler = new WriterSocketHandler(writerThreadList, writerPort, allWritters, bookList, book_id);
            writersockethandler.start();
            ReaderSocketHandler readersockethandler = new ReaderSocketHandler(readerThreadList, readerPort, allReaders, bookList, book_id);
            readersockethandler.start();
            System.out.println("Biblioteka włączona");
            while (true) {
                Thread.sleep(1000);
                System.out.print(".");//node dot
            }
        } catch (Exception e) {
            System.out.println("bład...");
        }
    }
}

class ReaderSocketHandler extends Thread{
    private List<Reader> readerList;
    private int readerPort;
    private List<String> allReaders;
    private List<Book> bookList;
    private Lib_number book_id;
    public ReaderSocketHandler( List<Reader> readerList, int readerPort, List<String> allReaders, List<Book> bookList, Lib_number book_id){this.book_id=book_id; this.bookList=bookList; this.readerList=readerList; this.readerPort=readerPort; this.allReaders=allReaders;}
    public synchronized void run(){
        try{
            ServerSocket serversocket=new ServerSocket(readerPort);
            while(true){
                Socket socket=serversocket.accept();
                System.out.println("new reader connection ");
                Reader newReader=new Reader(socket, allReaders, bookList, book_id);
                newReader.start();
                readerList.add(newReader);
                System.out.println("rozmiar readerList: "+readerList.size());
                for(int i=0;i<readerList.size();i++){
                    System.out.println("interacja"+i);
                    if(readerList.get(i).isToDelete()){readerList.remove(i); System.out.println("deletingggg "+i);}
                }
            }
        }catch (IOException e){System.out.println("io");}
    }
}

class Reader extends Thread{
    private List<Book> bookList;
    private List<String> allReaders;
    private boolean toDelete=false;
    private boolean authorised=false;
    private String current_reader="";
    private Socket readerSocket;
    private Lib_number book_id;
    public boolean isToDelete() {
        return toDelete;
    }
    public Reader(Socket readerSocket,List<String> allReaders, List<Book> bookList, Lib_number book_id){this.book_id=book_id; this.bookList=bookList; this.allReaders=allReaders; this.readerSocket=readerSocket;}
    public synchronized void run(){
        try {
            InputStream in = readerSocket.getInputStream();
            OutputStream out = readerSocket.getOutputStream();
            PrintStream ps=new PrintStream(out);
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            String buffor="";
            ps.println("Podaj nazwe czytelnika...");
            buffor=br.readLine(); System.out.println(buffor);
            for (int i=0;i<allReaders.size();i++){
                System.out.print(allReaders.get(i)+" "+i);
                if(buffor.equals(allReaders.get(i))){
                    ps.println("autoryzacja pomyślna, jesteś "+buffor+" list(lista książek)/read/giveBack/quit");
                    System.out.print(" ----OK");
                    authorised=true;
                    current_reader=buffor;
                    buffor="";
                }
                System.out.println();
            }
            if(authorised==false){ps.println("błąd autoryzacji"); System.out.println("błąd autoryzacji");}
            while (toDelete == false && buffor!=null && authorised==true) {
                buffor=br.readLine();
                System.out.println(buffor);
                if (buffor==null){}
                else if (buffor.equals("giveBack")){
                    System.out.println("giveBack"); buffor="";
                    System.out.println("Podaj tytuł ksiązki"); ps.println("Podaj tytuł ksiązki");
                    buffor=br.readLine();
                    boolean czy_znaleziono=false;
                    for(int i=0;i<bookList.size();i++){
                        if(bookList.get(i).getTitle().equals(buffor) && bookList.get(i).checkReader(current_reader)){
                            bookList.get(i).removeReader(current_reader);
                            i = bookList.size();
                            czy_znaleziono = true;
                        }
                    }
                    if(czy_znaleziono){
                        System.out.println("Pomyślnie oddano książkę"); ps.println("Pomyślnie oddano książkę");
                    }
                    else{
                        System.out.println("Nie masz takiej książki"); ps.println("Nie masz takiej książki");
                    }
                }
                else if (buffor.equals("list")){
                    System.out.println("list"); buffor="";
                    for (int i=0;i<bookList.size();i++){
                        System.out.println("Tytuł: "+bookList.get(i).getTitle()+" Autor: "+bookList.get(i).getAuthor()+" Data wydania: "+bookList.get(i).getRealase_date());
                        buffor=buffor+"ID: "+bookList.get(i).getLib_number()+" Tytuł: "+bookList.get(i).getTitle()+" Autor: "+bookList.get(i).getAuthor()+" Data wydania: "+bookList.get(i).getRealase_date()+" Czy są czytelnicy: "+bookList.get(i).areReaders()+" ||| ";
                    }
                    ps.println(buffor);

                }
                else if(buffor.equals("read")){
                    System.out.println("read"); buffor="";
                    System.out.println("Podaj tytuł ksiązki"); ps.println("Podaj tytuł ksiązki");
                    buffor=br.readLine();
                    boolean czy_znaleziono=false;
                    for(int i=0;i<bookList.size();i++){
                        if(bookList.get(i).getTitle().equals(buffor)){
                            if(bookList.get(i).getBlocked()==false) {
                                bookList.get(i).addReader(current_reader);
                                buffor = bookList.get(i).getContents();
                                i = bookList.size();
                                czy_znaleziono = true;
                            }
                            else {
                                buffor = "*** książka zablokowana ***";
                                i = bookList.size();
                                czy_znaleziono = true;
                            }
                        }
                    }
                    if(czy_znaleziono){
                        System.out.println("Treść: "+buffor); ps.println("Treść: "+buffor);
                    }
                    else{
                        System.out.println("Nie ma takiej książki"); ps.println("Nie ma takiej książki");
                    }
                }
                else if(buffor.equals("quit")){
                    System.out.println("quit"); buffor=null;
                }
                else {
                    System.out.println("zły wybór.."); ps.println("zły wybór.."); buffor="";
                }
            }
            toDelete=true;
            System.out.println(current_reader+" has disconnected");
        }catch(IOException e){}
    }
}

class WriterSocketHandler extends Thread{

    private List<Writer> writerList;
    private int writerPort;
    private List<String> allWritters;
    private List<Book> bookList;
    private Lib_number book_id;
    public WriterSocketHandler( List<Writer> writerList, int writerPort, List<String> allWritters, List<Book> bookList, Lib_number book_id){this.book_id=book_id; this.bookList=bookList; this.writerList=writerList; this.writerPort=writerPort; this.allWritters=allWritters;}
    public synchronized void run(){
        try{
            ServerSocket serversocket=new ServerSocket(writerPort);
            while(true){
                Socket socket=serversocket.accept();
                System.out.println("new writer connection ");
                Writer newWriter=new Writer(socket, allWritters, bookList, book_id);
                newWriter.start();
                writerList.add(newWriter);
                System.out.println("rozmiar writerList: "+writerList.size());
                for(int i=0;i<writerList.size();i++){
                    System.out.println("interacja"+i);
                    if(writerList.get(i).isToDelete()){writerList.remove(i); System.out.println("deletingggg "+i);}
                }
            }
        }catch (IOException e){System.out.println("io");}
    }
}

class Writer extends Thread{
    private List<Book> bookList;
    private List<String> allWritters;
    private boolean toDelete=false;
    private boolean authorised=false;
    private String current_author="";
    private Socket writerSocket;
    private Lib_number book_id;
    public boolean isToDelete() {
        return toDelete;
    }
    public boolean isTitleFree(String title, List<Book> bookList){
        boolean free=true;
        for(int i=0;i<bookList.size();i++){
            if(bookList.get(i).getTitle().equals(title)){free=false;}
        }
        System.out.println("Returning "+free);
        return free;

    }
    public Writer(Socket writerSocket,List<String> allWritters, List<Book> bookList, Lib_number book_id){this.book_id=book_id; this.bookList=bookList; this.allWritters=allWritters; this.writerSocket=writerSocket;}
    public synchronized void run(){
        try {
            InputStream in = writerSocket.getInputStream();
            OutputStream out = writerSocket.getOutputStream();
            PrintStream ps=new PrintStream(out);
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            String buffor="";
            ps.println("Podaj nazwe pisarza...");
            buffor=br.readLine(); System.out.println(buffor);
            for (int i=0;i<allWritters.size();i++){
                System.out.print(allWritters.get(i)+" "+i);
                if(buffor.equals(allWritters.get(i))){
                    ps.println("autoryzacja pomyślna, jesteś "+buffor+" list(lista książek)/mylist(lista moich ksiazek)/read/giveBack/add/remove/unblock/block/quit");
                    System.out.print(" ----OK");
                    authorised=true;
                    current_author=buffor;
                    buffor="";
                }
                System.out.println();
            }
            if(authorised==false){ps.println("błąd autoryzacji"); System.out.println("błąd autoryzacji");}
            while (toDelete == false && buffor!=null && authorised==true) {
                buffor=br.readLine();
                System.out.println(buffor);
                if (buffor==null){}
                else if (buffor.equals("giveBack")){
                    System.out.println("giveBack"); buffor="";
                    System.out.println("Podaj tytuł ksiązki"); ps.println("Podaj tytuł ksiązki");
                    buffor=br.readLine();
                    boolean czy_znaleziono=false;
                    for(int i=0;i<bookList.size();i++){
                        if(bookList.get(i).getTitle().equals(buffor) && bookList.get(i).checkReader(current_author)){
                            bookList.get(i).removeReader(current_author);
                            i = bookList.size();
                            czy_znaleziono = true;
                        }
                    }
                    if(czy_znaleziono){
                        System.out.println("Pomyślnie oddano książkę"); ps.println("Pomyślnie oddano książkę");
                    }
                    else{
                        System.out.println("Nie masz takiej książki"); ps.println("Nie masz takiej książki");
                    }
                }
                else if (buffor.equals("mylist")){
                    System.out.println("mylist"); buffor="";
                    for (int i=0;i<bookList.size();i++){
                        if(bookList.get(i).getAuthor().equals(current_author)) {
                            System.out.println("Tytuł: " + bookList.get(i).getTitle() + " Autor: " + bookList.get(i).getAuthor() + " Data wydania: " + bookList.get(i).getRealase_date() + " Czy są czytelnicy: " + bookList.get(i).areReaders() + " Czy zablokowana: " + bookList.get(i).getBlocked() + " ||| ");
                            buffor = buffor + "ID: " + bookList.get(i).getLib_number() + " Tytuł: " + bookList.get(i).getTitle() + " Autor: " + bookList.get(i).getAuthor() + " Data wydania: " + bookList.get(i).getRealase_date() + " Czy są czytelnicy: " + bookList.get(i).areReaders() + " Czy zablokowana: " + bookList.get(i).getBlocked() + " ||| ";
                        }
                    }
                    ps.println(buffor);

                }
                else if (buffor.equals("list")){
                    System.out.println("list"); buffor="";
                    for (int i=0;i<bookList.size();i++){
                        System.out.println("Tytuł: "+bookList.get(i).getTitle()+" Autor: "+bookList.get(i).getAuthor()+" Data wydania: "+bookList.get(i).getRealase_date()+" Czy są czytelnicy: "+bookList.get(i).areReaders()+" Czy zablokowana: "+bookList.get(i).getBlocked()+" ||| ");
                        buffor=buffor+"ID: "+bookList.get(i).getLib_number()+" Tytuł: "+bookList.get(i).getTitle()+" Autor: "+bookList.get(i).getAuthor()+" Data wydania: "+bookList.get(i).getRealase_date()+" Czy są czytelnicy: "+bookList.get(i).areReaders()+" Czy zablokowana: "+bookList.get(i).getBlocked()+" ||| ";
                    }
                    ps.println(buffor);

                }
                else if(buffor.equals("read")){
                    System.out.println("read"); buffor="";
                    System.out.println("Podaj tytuł ksiązki"); ps.println("Podaj tytuł ksiązki");
                    buffor=br.readLine();
                    boolean czy_znaleziono=false;
                    for(int i=0;i<bookList.size();i++){
                        if(bookList.get(i).getTitle().equals(buffor)){
                            if(bookList.get(i).getBlocked()==false) {
                                bookList.get(i).addReader(current_author);
                                buffor = bookList.get(i).getContents();
                                i = bookList.size();
                                czy_znaleziono = true;
                            }
                            else {
                                buffor = "*** książka zablokowana ***";
                                i = bookList.size();
                                czy_znaleziono = true;
                            }
                        }
                    }
                    if(czy_znaleziono){
                        System.out.println("Treść: "+buffor); ps.println("Treść: "+buffor);
                    }
                    else{
                        System.out.println("Nie ma takiej książki"); ps.println("Nie ma takiej książki");
                    }
                }
                else if(buffor.equals("add")){
                    String title="";
                    String contents="";
                    LocalDate realase_date;
                    System.out.println("add"); buffor="";
                    System.out.println("Podaj tytuł"); ps.println("Podaj tytuł");
                    title=br.readLine();
                    System.out.println("Podaj datę wydania RRRR-MM-DD"); ps.println("Podaj datę wydania RRRR-MM-DD");
                    try {
                        realase_date = LocalDate.parse(br.readLine());
                    }catch(DateTimeException e){
                        realase_date=LocalDate.now();
                    }
                    System.out.println("Podaj treść"); ps.println("Podaj treść");
                    contents=br.readLine();
                    if(this.isTitleFree(title, bookList)){
                        Book new_book=new Book(book_id.getLibrary_number(), current_author, title, realase_date, contents, false, false);
                        book_id.setLibrary_number(book_id.getLibrary_number()+1);
                        bookList.add(new_book);
                        System.out.println("Pomyślnie dodano ksiązkę"); ps.println("Pomyślnie dodano ksiązkę");
                    }
                    else{
                        System.out.println("Książka o takim tytule już jest!"); ps.println("Książka o takim tytule już jest!");
                    }
                    buffor="";
                }
                else if(buffor.equals("remove")){
                    System.out.println("remove"); buffor="";
                    System.out.println("Podaj tytuł książki do usunięcia"); ps.println("Podaj tytuł książki do usunięcia");
                    buffor=br.readLine();
                    boolean czy_usunieto=false;
                    for (int i=0;i<bookList.size();i++){
                        if(bookList.get(i).getTitle().equals(buffor)){
                            if (bookList.get(i).getAuthor().equals(current_author)){
                                czy_usunieto=true;
                                buffor="";
                                bookList.remove(i);
                            }
                        }
                    }
                    if(czy_usunieto){
                        System.out.println("Pomyślnie usunięto książkę"); ps.println("Pomyślnie usunięto książkę");
                    }
                    else{
                        System.out.println("Nie można usunąć książki, nie jesteś jej autorem albo ona nie istnieje"); ps.println("Nie można usunąć książki, nie jesteś jej autorem albo ona nie istnieje");
                        buffor="";
                    }

                }
                else if(buffor.equals("block")){
                    System.out.println("block"); buffor="";
                    System.out.println("Podaj tytuł książki do zablokowania"); ps.println("Podaj tytuł książki do zablokowania");
                    buffor=br.readLine();
                    boolean czy_zablokowano=false;
                    for (int i=0;i<bookList.size();i++){
                        if(bookList.get(i).getTitle().equals(buffor)){
                            if (bookList.get(i).getAuthor().equals(current_author)){
                                czy_zablokowano=true;
                                buffor="";
                                bookList.get(i).setBlocked(true);
                            }
                        }
                    }
                    if(czy_zablokowano){
                        System.out.println("Pomyślnie zablokowano książkę"); ps.println("Pomyślnie zablokowano książkę");
                    }
                    else{
                        System.out.println("Nie można zablokować książki, nie jesteś jej autorem albo ona nie istnieje"); ps.println("Nie można zablokować książki, nie jesteś jej autorem albo ona nie istnieje");
                        buffor="";
                    }
                }
                else if(buffor.equals("unblock")){
                    System.out.println("block"); buffor="";
                    System.out.println("Podaj tytuł książki do odblokowania"); ps.println("Podaj tytuł książki do odblokowania");
                    buffor=br.readLine();
                    boolean czy_odblokowano=false;
                    for (int i=0;i<bookList.size();i++){
                        if(bookList.get(i).getTitle().equals(buffor)){
                            if (bookList.get(i).getAuthor().equals(current_author)){
                                czy_odblokowano=true;
                                buffor="";
                                bookList.get(i).setBlocked(false);
                            }
                        }
                    }
                    if(czy_odblokowano){
                        System.out.println("Pomyślnie obblokowano książkę"); ps.println("Pomyślnie odblokowano książkę");
                    }
                    else{
                        System.out.println("Nie można odblokować książki, nie jesteś jej autorem albo ona nie istnieje"); ps.println("Nie można zablokować książki, nie jesteś jej autorem albo ona nie istnieje");
                        buffor="";
                    }
                }
                else if(buffor.equals("quit")){
                    System.out.println("quit"); buffor=null;
                }
                else {
                    System.out.println("zły wybór.."); ps.println("zły wybór.."); buffor="";
                }
            }
            toDelete=true;
            System.out.println(current_author+" has disconnected");
        }catch(IOException e){}
    }
}

class Book {
    private List<String> currentReaders;
    private int lib_number;
    private String author;
    private String title;
    private LocalDate realase_date;
    private String contents;
    private Boolean borrowed;
    private Boolean blocked;

    public Book() {currentReaders=new ArrayList<>();}
    public Book(int lib_number, String author, String title, LocalDate realase_date, String contents, Boolean borrowed, Boolean blocked) {
        currentReaders=new ArrayList<>();
        this.lib_number = lib_number;
        this.author = author;
        this.title = title;
        this.realase_date = realase_date;
        this.contents = contents;
        this.borrowed = borrowed;
        this.blocked = blocked;
    }

    public boolean checkReader(String reader){
        boolean czy_jest_taki_czytelnik=false;
        for(int i=0;i<currentReaders.size();i++){
            if(currentReaders.get(i).equals(reader)){
                czy_jest_taki_czytelnik=true;
            }
        }
        return czy_jest_taki_czytelnik;
    }

    public void addReader(String reader){
        boolean czy_juz_dodany=false;//zeby nie dublowac tych samych czytelnikow
        for(int i=0;i<currentReaders.size();i++){
            if(currentReaders.get(i).equals(reader)){
                czy_juz_dodany=true;
            }
        }
        if (czy_juz_dodany){}
        else{
            currentReaders.add(reader);
        }
    }
    public void removeReader(String reader){
        for(int i=0;i<currentReaders.size();i++){
            if(currentReaders.get(i).equals(reader)){currentReaders.remove(i);}
        }
    }
    public boolean areReaders(){
        if(currentReaders.isEmpty()){return false;}
        else {return true;}
    }

    public int getLib_number() {
        return lib_number;
    }
    public void setLib_number(int lib_number) {
        this.lib_number = lib_number;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public LocalDate getRealase_date() {
        return realase_date;
    }
    public void setRealase_date(LocalDate realase_date) {
        this.realase_date = realase_date;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public Boolean getBorrowed() {
        return borrowed;
    }
    public void setBorrowed(Boolean borrowed) {
        this.borrowed = borrowed;
    }
    public Boolean getBlocked() {
        return blocked;
    }
    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }
}

class Lib_number{
    private int library_number=0;

    public int getLibrary_number() {
        return library_number;
    }

    public void setLibrary_number(int library_number) {
        this.library_number = library_number;
    }
}