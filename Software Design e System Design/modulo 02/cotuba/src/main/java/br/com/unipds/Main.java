package br.com.unipds;

import br.com.unipds.options.OptionsDTO;
import br.com.unipds.options.OptionsService;

public class Main {
    private final OptionsService optionsService = new OptionsService();
    private final ApplicationService applicationService = new ApplicationService();

    void main(String[] args) {
        int exitCode = execute(args);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    int execute(String[] args) {
        OptionsDTO options = this.optionsService.getOptionsFromArgs(args);
        if (options == null) return 1;
        try {
            applicationService.processEbook(options);
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