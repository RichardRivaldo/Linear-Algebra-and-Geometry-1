import java.util.Scanner;

public class Interpolasi {
    public static void PolinomInterpolasi(int jumlahTitik){

        /* Scanner input */
        Scanner sc = new Scanner(System.in);
    
        Matrix augInterpolasi = new Matrix(jumlahTitik, jumlahTitik + 1);
    
        for(int i = 0; i < Matrix.GetRow(augInterpolasi); i++){
            System.out.printf("Masukkan nilai X%d: ", i + 1);
            double X = sc.nextDouble();
            System.out.printf("Masukkan nilai Y%d: ", i + 1);
            double Y = sc.nextDouble();
            for(int j = 0; j < Matrix.GetKol(augInterpolasi) - 1; j++){
                Matrix.SetElmt(augInterpolasi, i, j, Math.pow(X, j));
            Matrix.SetElmt(augInterpolasi, i, Matrix.GetKol(augInterpolasi) - 1, Y);
            }
        }

        /* Matrix.TulisMatrix(augInterpolasi); */

        System.out.print("Masukkan titik yang ingin diestimasi: ");
        double titikInterpolasi = sc.nextDouble();

        double[] hasilInterpolasi = SPL.gaussJordan(augInterpolasi);

        double hasil = hasilInterpolasi[0];
        for(int i = 1; i < hasilInterpolasi.length; i++){
            hasil += Math.pow(titikInterpolasi, i) * hasilInterpolasi[i];
        }

        boolean isNan = false;

        for(int i = 0; i < hasilInterpolasi.length; i++){
            if(Double.isNaN(hasilInterpolasi[i])){
                isNan = true;
                break;
            }
        }

        if(isNan){
            System.out.println("Polinom interpolasi tidak terdefinisi!");
        }
        else{
            System.out.println("Polinom interpolasi yang dihasilkan dari titik-titik tersebut: ");
            System.out.printf("P%d(x) = %.4f ", jumlahTitik - 1, hasilInterpolasi[0]);
            for(int i = 1; i < hasilInterpolasi.length; i++){
                if(hasilInterpolasi[i] > 0 || hasilInterpolasi[i] <0){
                    System.out.print("+ ");
                }
                else{
                    System.out.print("");
                }
                if(hasilInterpolasi[i] > 0 && i == 1){
                    System.out.printf("%.4fx ", hasilInterpolasi[i]);
                }
                else if(hasilInterpolasi[i] > 0){
                    System.out.printf("%.4fx^%d ", hasilInterpolasi[i], i);
                }
                else if(hasilInterpolasi[i] < 0){
                    System.out.printf("(%.4fx^%d) ", hasilInterpolasi[i], i);
                }
                else{
                    System.out.print("");
                }
            }
            System.out.print("\n");
        }
        int derajatInterpolasi = jumlahTitik - 1;

        System.out.println("Hasil estimasi dengan polinom interpolasi di atas adalah: ");
        System.out.printf("P%d(%.4f) = %.4f\n", derajatInterpolasi, titikInterpolasi, hasil);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Masukkan jumlah titik: ");
        int jumlahTitik = sc.nextInt();
        while(jumlahTitik < 0){
            System.out.print("Jumlah titik invalid. Masukkan jumlah baru: ");
            jumlahTitik = sc.nextInt();
        }
        PolinomInterpolasi(jumlahTitik);
    }
}