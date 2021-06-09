package com.javase.programmingcomplete.part2.flowcontrol;

public class Switch {

    /*
    A ordem dos cases no switch não importa. Obs: Se colocar o default em primeiro, ele executa normal.
    E se não colocar o break, ele executa o próximo normal tbm.
     */
    public static void main(String[] args) {
        char op = 'C';
        switch (op) {
            default:
                System.out.println("None");
                break;
            case 'A':
                System.out.println("A");
                break;
            case 'B':
                System.out.println('B');
                break;

        }
    }

}