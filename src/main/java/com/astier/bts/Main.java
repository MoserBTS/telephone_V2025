package com.astier.bts;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static String numero;
    static String pays = "", region = "", departement = "";

    public static void main(String[] args) {
        System.out.println("Saisir un numéro: ");
        numero = sc.nextLine();
        //formatage numero indicatif pays
        numero = switch (numero) {
            case String n when n.matches("\\+[1-9]{2}[0-7][0-9]{9}") -> numero;
            case String n when n.matches("\\+[1-9]{2}[1-7][0-9]{8}") -> {
                StringBuilder sb = new StringBuilder(numero);
                sb.insert(3, '0');
                yield sb.toString();
            }
            case String n when n.matches("0[1-7][0-9]{8}") -> {
                StringBuilder sb = new StringBuilder(numero);
                sb.insert(0, "+33");
                yield sb.toString();
            }
            default -> "erreur numero";
        };
        System.out.println(numero);

        pays = switch (numero) {
            case String p when p.matches("\\+33.*") -> {
                region = switch (numero) {
                    case String sr when sr.matches("\\+3301.*") -> {
                        departement = switch (numero) {
                            case String srd when srd.matches(".....25.*") -> "zone 25";
                            case String srd when srd.matches(".....26.*") -> "zone 26";
                            default -> "erreur département";
                        };
                        yield "Paris";
                    }
                    case String sr when sr.matches("\\+3302.*") -> {
                        departement = switch (numero) {
                            case String srd when srd.matches(".....25.*") -> "zone 25";
                            case String srd when srd.matches(".....26.*") -> "zone 26";
                            default -> "erreur département";
                        };
                        yield "Bretagne";
                    }
                    default -> "erreur région";
                };
                yield "France";
            }
            case String p when p.matches("\\+39.*") -> {
                System.out.println("");
                yield "Italie";
            }
            default -> "erreur pays";
        };
        System.out.println(pays + " " + region + " " + departement);
    }
}