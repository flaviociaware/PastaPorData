import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// Para executar: java -jar PastaPorData.jar
public class PastaPorData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Bem vindo!");
		System.out.println(" - facebook.com/CIAware");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println(" ");
		System.out.println(" ");
		System.out.println("* 1/3 Lendo a pasta ");

		List<String> diretorios = new ArrayList<String>();

		File f = new File(".");
		File[] arquivos = f.listFiles(); // retorna um array de Files

		List<File> arquivosOrigem = new ArrayList<File>();

		for (File arq : arquivos) {

			if (arq.isFile() && (!arq.isDirectory())) {
				
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(arq.lastModified());

				String dir = sdf.format(cal.getTime());

				if (!diretorios.contains(dir)) {
					diretorios.add(dir);
				}

				arquivosOrigem.add(arq);
			}

		}
		System.out.println("- Diretórios: " + diretorios.size());
		System.out.println("- Arquivos..: " + arquivosOrigem.size());

		System.out.println(" ");
		System.out.println(" ");
		System.out
				.println("* 2/3 Criando as pastas com base nas datas dos arquivos");
		for (String dir : diretorios) {
			File theDir = new File(dir);

			System.out.println(" ");
			System.out.print("Diretorio " + dir + ": ");

			// if the directory does not exist, create it
			if (!theDir.exists()) {
				System.out.print("criando... ");
				boolean result = false;

				try {
					theDir.mkdir();
					result = true;
				} catch (Exception se) {
					// handle it
					System.out.print(" [Erro " + se.getMessage() + "] ");

				}

				if (result) {
					System.out.print(" [ok]");
				}
			} else {
				System.out.println(" [Já existe] ");

			}
		}

		System.out.println(" ");
		System.out.println(" ");
		System.out
				.println("* 3/3 Movendo os arquivos para as respectivas pastas ");

		
		for (File file : arquivosOrigem) {
			try {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(file.lastModified());

				String dir = sdf.format(cal.getTime());

				String moverPara = "./" + dir + "/" + file.getName();
				System.out.println(" ");
				System.out.print("- Movendo " + file.getName()+ ": ");
				
				if (file.renameTo(new File(moverPara))) {
					System.out.print(" [ok]");
				} else {
					System.out.print(" [falha]");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(" [erro " + e.getMessage() + "]");
			}
		}
		
	}
}
