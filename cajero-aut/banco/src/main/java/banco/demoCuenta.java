package banco;

import java.util.Scanner;
import java.io.IOException;
import java.util.Locale;
import banco.CuentaBancaria;
import banco.TipoCuenta;

public class demoCuenta {
    public static void main(String[] args) throws IOException, InterruptedException {
        /* Permite mostrar siempre el menu despues de c/operacion*/
        new ProcessBuilder("cmd","/c", "cls").inheritIO().start().waitFor();

        Scanner data = new Scanner(System.in);
        data.useLocale(Locale.ENGLISH);

        String name;
        long nroCuenta;
        double importe;
        /* ------------------------- */
        int opcion;
        char bloqueo;
        /* ------------------------- */
        /* Obtenemos los valores de tipo de cuenta para cargar*/
        /* ------------------------- */
        TipoCuenta[] tCuenta = TipoCuenta.values();
        /* ------------------------- */
        CuentaBancaria cb;
        System.out.println("Creacion de cuenta");
        System.out.println("----------------------------");
        System.out.println("Ingrese los datos de la Cuenta: ");
        System.out.print("\tNombre Cliente");
        name = data.nextLine();
        System.out.print("\tN° Cuenta: ");
        nroCuenta = data.nextLong();
        System.out.println("\tTipos de Cuenta");
        /* ------------------------- */
        for (int i = 0; i< tCuenta.length; i++) {
            System.out.println("\t\t" + (i + 1) + "| " + tCuenta[i]);
        }
        System.out.print("\tSeleccione una Opcion: ");
        opcion = data.nextInt();
        /* ------------------------- */
        cb = new CuentaBancaria(nroCuenta, name, 0, tCuenta[opcion - 1]);
        /* ------------------------- */
        /* MENU DE OPCIONES*/
        /* ------------------------- */
        do {
            System.out.println("\n ---------------------------------------- ");
            System.out.println("|            Menu de Opciones           |");
            System.out.println(" ------------------------------------------ ");
            System.out.println(" 1) Mostrar Datos Cuenta");
            System.out.println(" 2) Ingresar Dinero");
            System.out.println(" 3) Retirar Dinero");
            System.out.println(" 4) Bloquear Cuenta");
            System.out.println(" 5) Desbloquear Cuenta");

            System.out.print("Seleccione una Opcion\n 0->sale:");
            opcion=data.nextInt();

            new ProcessBuilder("cmd", "/c","cls").inheritIO().start().waitFor();

            switch (opcion){
                case 0:
                    System.out.println("Adios...\n");
                    break;

                case 1:
                    cb.mostrarCuenta();
                    break;

                case 2:
                    if (cb.getEstadoCta()){
                        cb.setDeposito(data);
                    }
                    break;

                case 3:
                    if (cb.getEstadoCta()){
                        System.out.print("Ingrese el importe a retirar: ");
                        importe = data.nextDouble();

                        if (!cb.setRetiro(importe)){
                            System.out.println("No tiene saldo Suficiente para retirar!");
                        }
                    }
                    break;

                case 4:
                    if (!cb.getBloqueoCta())
                        System.out.print("La cuenta ya se encuentra Bloqueada");
                    else{
                        System.out.print("¿Desea bloquear la cuenta? s/n");
                        bloqueo = (char) System.in.read();
                        if(bloqueo==(char) 'S' || bloqueo==(char) 's'){
                            cb.setBloqueoCta(false);
                            cb.mensExito();
                        }
                    }
                    break;

                case 5:
                    if (cb.getBloqueoCta())
                        System.out.print("La cuenta ya se encuentra habilitada");
                    else{
                        System.out.print("Desea habilitar la cuenta? s/n");
                        bloqueo = (char) System.in.read();
                        if(bloqueo==(char)'S' || bloqueo ==(char) 's'){
                            cb.setBloqueoCta(true);
                            cb.mensExito();
                        }
                    }
                    break;

                default:
                    System.out.println("Seleccion no valida...");
            }
        }while (opcion!=0);
        data.close();
    }
}
