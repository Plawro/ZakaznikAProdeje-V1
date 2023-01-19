//Code by Plawro 2023
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        List<Zakaznik> zakaznici = new ArrayList<>();

        //---   INTERAKCE   ---

        //Přidání do listu (způsob 1)
        zakaznici.add(new Zakaznik("Pavel Novotný", 7));
        zakaznici.add(new Zakaznik("Jan Malý", 1));
        zakaznici.add(new Zakaznik("Petr Novák", 2));
        //Přidání do listu (způsob 2)
        addToList(zakaznici,"Jaromír Cena",8);

        //Smazání zákazníka
        removeFromList(zakaznici,2);
        //Smazání posledního zákazníka
        removeLastFromList(zakaznici);

        //Vypsání listu zákazníků do terminálu
        showList(zakaznici);

        //Zvyšování prodejů u zákazníka (Pozor, tady list (metoda get()) začíná od 0!!)
        zakaznici.get(1).zvysPocetProdeju(3);

        //Zapsání do souboru
        writeToFileList("seznam.txt",":",zakaznici);
        //Vypsání listu ze souboru
        showFileList(zakaznici,"seznam.txt");


    }

    private static void writeToFileList(String soubor, String oddelovac, List<Zakaznik> seznamZakazniku){
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(soubor)))) {
            for(Zakaznik zakaznik : seznamZakazniku){
                writer.println(
                    zakaznik.getJmeno()+oddelovac+zakaznik.getPocetProdeju()
                );
            }

        } catch (IOException e) {
            throw new RuntimeException("Chyba při zápisu: " +e);
        }
    }

    private static void showFileList(List<Zakaznik> seznamZakazniku, String soubor) {
        List<String> printList = new ArrayList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(new File(soubor), "UTF-8");

            while (sc.hasNextLine()) {
                printList.add(new String(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Nenalezen soubor: " + soubor + ": " + e.getLocalizedMessage());
        }
        /*catch (IOException e) {
            System.err.println("Chyba čtení ze souboru " + soubor + ": " + e.getLocalizedMessage());
        } */
        catch (
                InputMismatchException e) {
            System.err.println("Nesprávný formát dat v souboru " + soubor + ": " + e.getLocalizedMessage());
        } finally {
            System.out.println("--- Výpis ze záznamu ---");
            int poradi=0;
            for(Zakaznik zakaznik : seznamZakazniku){
                poradi++;
                System.out.println(poradi +") "+zakaznik);
            }
            //System.out.println(printList);
            System.out.println("");
            sc.close();
        }
    }

    private static void addToList(List<Zakaznik> seznamZakazniku, String jmeno, int pocetProdeju) throws Exception{
        if(pocetProdeju > 0){
            seznamZakazniku.add(new Zakaznik(jmeno,pocetProdeju));
        } else{
            throw new Exception("Počet musí být kladný, váš počet: "+pocetProdeju);
        }

    private static void showList(List<Zakaznik> seznamZakazniku){
        int poradi = 0;
        System.out.println("--- Výpis z listu ---");
        for(String zakaznik : printList){
            poradi++;
            System.out.println(poradi +") "+zakaznik);
        }
        System.out.println("");
    }

    private static void removeFromList(List<Zakaznik> seznamZakazniku, int poradi){

            try {
                seznamZakazniku.remove(poradi-1); //List zacina od 0, po uprave od 1
            }catch (IndexOutOfBoundsException e){
                throw new RuntimeException("Chyba při interakci: " +e);
            }
    }

    private static void removeLastFromList(List<Zakaznik> seznamZakazniku){
        seznamZakazniku.remove(seznamZakazniku.size()-1);
    }
}
