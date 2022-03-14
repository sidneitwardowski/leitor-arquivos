package br.com.empresa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import br.com.empresa.services.LeituraService;

public class Principal {

	public static void main(String[] args) throws IOException, InterruptedException {
		LeituraService service = new LeituraService();

		WatchService watchService = FileSystems.getDefault().newWatchService();

		Path caminhoEntrada = Paths.get(System.getProperty("user.home").concat(File.separator).concat("data")
				.concat(File.separator).concat("in"));

		Path caminhoSaida = Paths.get(System.getProperty("user.home").concat(File.separator).concat("data")
				.concat(File.separator).concat("out"));

		Path caminhoInconsistencias = Paths.get(System.getProperty("user.home").concat(File.separator).concat("data")
				.concat(File.separator).concat("erro"));

		if (!Files.isDirectory(caminhoEntrada)) {
			Files.createDirectories(caminhoEntrada);
		}

		if (!Files.isDirectory(caminhoSaida)) {
			Files.createDirectories(caminhoSaida);
		}

		caminhoEntrada.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		WatchKey key;
		BufferedWriter writer;

		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> event : key.pollEvents()) {

				String nomeArquivo = event.context().toString();
				Path caminhoArquivoEntrada = caminhoEntrada.resolve((Path) event.context());

				if (".dat".equalsIgnoreCase(nomeArquivo.substring(nomeArquivo.length() - 4))) {

					try {

						Path carminhoArquivoSaida = caminhoSaida.resolve(nomeArquivo.replace(".dat", ".done.dat"));
						List<String> linhas = Files.readAllLines(caminhoArquivoEntrada);

						service.processarRegistros(linhas);
						writer = Files.newBufferedWriter(carminhoArquivoSaida);
						writer.write(service.obterResultadoFormatado());
						writer.close();

					} catch (Exception e) {
						if (!Files.isDirectory(caminhoInconsistencias)) {
							Files.createDirectories(caminhoInconsistencias);
						}

						Files.move(caminhoArquivoEntrada, caminhoInconsistencias.resolve(nomeArquivo),
								StandardCopyOption.REPLACE_EXISTING);

						Path carminhoErro = caminhoInconsistencias
								.resolve(nomeArquivo.replace(".dat", "motivo-erro.dat"));
						writer = Files.newBufferedWriter(carminhoErro);
						writer.write(e.getMessage());
						writer.close();
					}

				}

			}
			key.reset();
			service.Limpar();

		}

	}
}
