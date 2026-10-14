package br.com.unipds;

import br.com.unipds.ebook.EBookServiceFactory;
import br.com.unipds.ebook.IEBookService;
import br.com.unipds.options.OptionsDTO;
import br.com.unipds.options.OptionsService;

public class Main {
    private OptionsService optionsService = new OptionsService();

    void main(String[] args) {
        int exitCode = executar(args);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    int executar(String[] args) {

        OptionsDTO options = this.optionsService.getOptionsFromArgs(args);
        if (options == null) return 1;
        try {
            IEBookService bookService = EBookServiceFactory.create(options.format());
            bookService.generate(options);
            System.out.println("Arquivo gerado com sucesso: " + options.outputFileName());
            return 0;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            if (options.isVerboseMode()) {
                System.err.println();
                ex.printStackTrace();
            }
            return 1;
        }
    }

}