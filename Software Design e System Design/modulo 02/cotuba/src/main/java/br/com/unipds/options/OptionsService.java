package br.com.unipds.options;

import org.apache.commons.cli.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class OptionsService {
    private final Options options = new Options();

    public OptionsService() {
        options.addOption(new Option("d", "dir", true,
                "Diretório que contém os arquivos md. Default: diretório atual."));

        options.addOption(new Option("f", "format", true,
                "Formato de saída do ebook. Pode ser: pdf ou epub. Default: pdf"));

        options.addOption(new Option("o", "output", true,
                "Arquivo de saída do ebook. Default: book.{formato}."));

        options.addOption(new Option("v", "verbose", false,
                "Habilita modo verboso."));
    }

    public OptionsDTO getOptionsFromArgs(String[] args) {
        CommandLineParser cmdParser = new DefaultParser();
        var helpFormatter = new HelpFormatter();
        CommandLine commandLine;

        try {
            commandLine = cmdParser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            helpFormatter.printHelp("cotuba", options);
            return new OptionsDTO(null, null, null, false);
        }

        Path sourceDir;
        String format;
        Path outputFileName;
        boolean verboseMode = false;

        try {
            verboseMode = commandLine.hasOption("verbose");
            String sourceDirName = commandLine.getOptionValue("dir");

            if (sourceDirName != null) {
                sourceDir = Paths.get(sourceDirName);
                if (!Files.isDirectory(sourceDir)) {
                    throw new IllegalArgumentException(sourceDirName + " não é um diretório.");
                }
            } else {
                sourceDir = Paths.get("");
                ;
            }

            String ebookFormat = commandLine.getOptionValue("format");

            if (ebookFormat != null) {
                format = ebookFormat.toLowerCase();
            } else {
                format = "pdf";
            }

            String outputFileNameAux = commandLine.getOptionValue("output");
            if (outputFileNameAux != null) {
                outputFileName = Paths.get(outputFileNameAux);
            } else {
                outputFileName = Paths.get("book." + format.toLowerCase());
            }
            if (Files.isDirectory(outputFileName)) {
                // deleta arquivos do diretório recursivamente
                Files.walk(outputFileName).sorted(Comparator.reverseOrder())
                        .map(Path::toFile).forEach(File::delete);
            } else {
                Files.deleteIfExists(outputFileName);
            }

            return new OptionsDTO(sourceDir, format, outputFileName, verboseMode);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            if (verboseMode) {
                System.err.println();
                ex.printStackTrace();
                throw new IllegalStateException(ex);
            }
        }
        return null;
    }

}

