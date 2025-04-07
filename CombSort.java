import java.io.*;
import java.time.Duration;
import java.time.Instant;

public class CombSort {

    private static long comparacoes = 0;
    private static long trocas = 0;
    private static final double fator = 1.3;

    public static void main(String[] args) throws IOException {
        int[] numeros = lerArquivo("numeros.txt");

        Instant inicio = Instant.now();
        combSort(numeros);
        Instant fim = Instant.now();
        Duration duracao = Duration.between(inicio, fim);

        salvarArquivo("ordenado_comb.txt", numeros, "Comb Sort", duracao, comparacoes, trocas);
    }

    public static void combSort(int[] vetor) {
        int gap = vetor.length;
        boolean houveTroca = true;

        while (gap > 1 || houveTroca) {
            gap = (int) (gap / fator);
            if (gap < 1) gap = 1;

            houveTroca = false;

            for (int i = 0; i + gap < vetor.length; i++) {
                comparacoes++;
                if (vetor[i] > vetor[i + gap]) {
                    int temp = vetor[i];
                    vetor[i] = vetor[i + gap];
                    vetor[i + gap] = temp;
                    trocas++;
                    houveTroca = true;
                }
            }
        }
    }

    public static int[] lerArquivo(String nomeArquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        String conteudo = br.readLine();
        br.close();
        conteudo = conteudo.replace("[", "").replace("]", "");
        String[] partes = conteudo.split(",");
        int[] numeros = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            numeros[i] = Integer.parseInt(partes[i].trim());
        }
        return numeros;
    }

    public static void salvarArquivo(String nomeArquivo, int[] vetor, String algoritmo, Duration duracao, long comp, long troca) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
        bw.write("Aluno: Marcos Eduardo Graciano Paula\n");
        bw.write("Algoritmo: " + algoritmo + "\n");
        bw.write("Tempo: " + formatarTempo(duracao) + "\n");
        bw.write("Comparações: " + comp + "\n");
        bw.write("Trocas: " + troca + "\n\n");
        bw.write("[");
        for (int i = 0; i < vetor.length; i++) {
            bw.write(String.valueOf(vetor[i]));
            if (i != vetor.length - 1) bw.write(", ");
        }
        bw.write("]\n");
        bw.close();
    }

    public static String formatarTempo(Duration duracao) {
        return String.format("%02d:%02d:%02d:%03d",
                duracao.toHours(),
                duracao.toMinutesPart(),
                duracao.toSecondsPart(),
                duracao.toMillisPart());
    }
}
