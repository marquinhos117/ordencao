import java.io.*;
import java.time.Duration;
import java.time.Instant;

public class SelectSort {

    private static long comparacoes = 0;
    private static long trocas = 0;

    public static void main(String[] args) throws IOException {
        int[] numeros = lerArquivo("numeros.txt");

        Instant inicio = Instant.now();
        selectionSort(numeros);
        Instant fim = Instant.now();
        Duration duracao = Duration.between(inicio, fim);

        salvarArquivo("ordenado_selection.txt", numeros, "Selection Sort", duracao, comparacoes, trocas);
    }

    public static void selectionSort(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < vetor.length; j++) {
                comparacoes++;
                if (vetor[j] < vetor[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = vetor[i];
                vetor[i] = vetor[min];
                vetor[min] = temp;
                trocas++;
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
            if (i != vetor.length - 1) {
                bw.write(", ");
            }
        }
        bw.write("]\n");
        bw.close();
    }

    public static String formatarTempo(Duration duracao) {
        long h = duracao.toHours();
        long m = duracao.toMinutesPart();
        long s = duracao.toSecondsPart();
        long ms = duracao.toMillisPart();
        return String.format("%02d:%02d:%02d:%03d", h, m, s, ms);
    }
}
