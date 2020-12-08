package com.exemple.java;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
public class Main {
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    private static int Cday;
    public static void ecrireDansFichier(String fichier, int message, boolean ajout) {
        try {
            File f = new File("fichier.txt");
            FileWriter fw = new FileWriter(fichier, ajout);
            PrintWriter pw = new PrintWriter(fw);
            pw.print(message);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void ecrireDansFichier(String fichier, String message, boolean ajout) {
        try {
            File f = new File("fichier.txt");
            FileWriter fw = new FileWriter(fichier, ajout);
            PrintWriter pw = new PrintWriter(fw);
            pw.print(message);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) throws IOException {
        // type MM yyyy
        System.out.println("       "+GREEN_UNDERLINED+"Welcome to our MEMO "+TEXT_RESET+"\n");
        String event;
        Scanner in = new Scanner(System.in);
        Scanner s = new Scanner(System.in);
        System.out.print(YELLOW_BOLD + "     Enter the Day :    " + TEXT_RESET);
        Cday = in.nextInt();
        System.out.print(CYAN_BOLD+"     Enter Month :      "+ TEXT_RESET);
        int month = in.nextInt();
        System.out.print(YELLOW_BOLD+"     Enter  year:      "+ TEXT_RESET);
        int year = in.nextInt();
        System.out.println("");
        //in.close();
        // chèques valables mois
        try {
            if (month < 1 || month > 12)
                throw new Exception("Invalid index for month: " + month);
            printCalendarMonthYear(month, year);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.print(CYAN_BOLD+"\n  Enter your event :    "+ TEXT_RESET);
        event = s.nextLine();
        in.close();
        if(month >=1 && month <=12 && Cday>=1 && Cday<=31){
            ecrireDansFichier("fichier.txt","* ",true);
            ecrireDansFichier("fichier.txt",year,true);
            ecrireDansFichier("fichier.txt","/",true);
            ecrireDansFichier("fichier.txt",month,true);
            ecrireDansFichier("fichier.txt","/",true);
            ecrireDansFichier("fichier.txt",Cday,true);
            ecrireDansFichier("fichier.txt",":",true);
            ecrireDansFichier("fichier.txt",event,true);
            ecrireDansFichier("fichier.txt","\n",true); }
        else{
            System.err.println("L'evenement n'a pas bien enregistrer");
        }
    }
    private static void printCalendarMonthYear ( int month, int year){
        Calendar cal = new GregorianCalendar();
        cal.clear();
        cal.set(year, month - 1, 1); // réglage du calendrier sur le mois et l'année fournis comme paramètres
        System.out.println(RED_BOLD+"Calendar for " + Cday + " " + cal.getDisplayName(Calendar.MONTH, Calendar.LONG,
                Locale.US) + " " + cal.get(Calendar.YEAR)+TEXT_RESET);//pour imprimer le calendrier pour le mois et l'année
        int firstWeekdayOfMonth = cal.get(Calendar.DAY_OF_WEEK);//quel jour de semaine était le premier du mois
        int numberOfMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //durée de jours dans un mois
        printCalendar(numberOfMonthDays, firstWeekdayOfMonth);
    }
    private static void printCalendar ( int numberOfMonthDays, int firstWeekdayOfMonth){
        int weekdayIndex = 0;
        System.out.println("Su  MO  Tu  We  Th  Fr  Sa"); // L'ordre des jours dépend de votre calendrier
        for (int day = 1; day < firstWeekdayOfMonth; day++) {
            System.out.print("    "); //cette boucle pour imprimer le premier jour à sa place
            weekdayIndex++;
        }
        for (int day = 1; day <= numberOfMonthDays; day++) {
            if (day < 10) // c'est juste pour une meilleure visualisation car le numéro d'unité prend bien sûr moins d'espace que 2
                System.out.print(day + " ");
            else {
                if (day == Cday) {
                    System.out.print(RED_UNDERLINED + day + TEXT_RESET);
                } else
                    System.out.print(day);
            }
            weekdayIndex++;
            if (weekdayIndex == 7) {
                weekdayIndex = 0;
                System.out.println();
            } else {
                System.out.print("  ");
            }
        }
    }
}