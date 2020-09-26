public class SPL {
    public static double[] gaussJordan(Matrix M) {
        int counter = 0;

        for (int i = 0; i < Matrix.GetRow(M); i++) {
            double pivot = Matrix.GetElmt(M, i, counter);

            if (pivot != 1){
                for (int j = 0; j < Matrix.GetKol(M); j++) {
                    Matrix.SetElmt(M, i, j, Matrix.GetElmt(M, i, j)/pivot);
                }
            }

            for (int k = 0; k < Matrix.GetRow(M); k++) {
                if (k == i) continue;

                double konst = Matrix.GetElmt(M, k, counter);
                for (int j = 0; j < Matrix.GetKol(M); j++) {
                    double a = Matrix.GetElmt(M, k, j);
                    double b = konst * Matrix.GetElmt(M, i, j);
                    Matrix.SetElmt(M, k, j, a-b);
                }
            }

            counter++;
        }
        
        
        double[] hasil = new double[Matrix.GetRow(M)];
        for (int i = 0; i < Matrix.GetRow(M); i++) {
            hasil[i] = Matrix.GetElmt(M, i, Matrix.GetKol(M)-1);
        }
        return hasil;
        
    }

    public static double[] gauss(Matrix M) {
        int counter = 0;

        for (int i = 0; i < Matrix.GetRow(M); i++) {
            double pivot = Matrix.GetElmt(M, i, counter);

            if (pivot != 1){
                for (int j = 0; j < Matrix.GetKol(M); j++) {
                    Matrix.SetElmt(M, i, j, Matrix.GetElmt(M, i, j)/pivot);
                }
            }

            for (int k = i+1; k < Matrix.GetRow(M); k++) {

                double konst = Matrix.GetElmt(M, k, counter);
                for (int j = 0; j < Matrix.GetKol(M); j++) {
                    double a = Matrix.GetElmt(M, k, j);
                    double b = konst * Matrix.GetElmt(M, i, j);
                    Matrix.SetElmt(M, k, j, a-b);
                }
            }

            counter++;
        }

        double[] hasil = new double[Matrix.GetRow(M)];
        hasil[Matrix.GetRow(M) - 1] = Matrix.GetElmt(M, Matrix.GetRow(M) - 1, Matrix.GetKol(M) - 1);
        for (int i = Matrix.GetRow(M)-2; i >= 0; i--) {
            double sum = 0;
            for (int j = i+1; j < Matrix.GetKol(M)-1; j++) {
                sum += hasil[j]*Matrix.GetElmt(M, i, j);
            }
            hasil[i] = Matrix.GetElmt(M, i, Matrix.GetKol(M) - 1) - sum;
        }
        return hasil;
        
    }

    public static void main(String[] args) {
        Matrix M;
        System.out.println("(Untuk spl, jumlah kolom = jumlah baris + 1)");
        M = Matrix.MakeMatrix();
        while (Matrix.GetKol(M) - Matrix.GetRow(M) != 1 ){
            System.out.println("Matriks tidak valid untuk SPL!\n");
            M = Matrix.MakeMatrix();
        }
        Matrix.TulisMatrix(M);
        System.out.println("\n");
        double[] x = gauss(M);
        double[] y = gaussJordan(M);
        for (int i = 0; i < y.length; i++) {
            System.out.print(x[i] + " "+ y[i]);
            System.out.println();
        }
    }
}
