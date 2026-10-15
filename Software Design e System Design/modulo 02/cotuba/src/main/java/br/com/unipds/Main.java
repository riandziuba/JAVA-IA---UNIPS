package br.com.unipds;

import br.com.unipds.options.IOptionsService;
import br.com.unipds.options.OptionsDTO;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Main {
    void main(String[] args) {
        int exitCode = execute(args);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    int execute(String[] args) {
        try(SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            var optionsService = container.select(IOptionsService.class).get();
            var applicationService = container.select(IApplicationService.class).get();
            OptionsDTO options = optionsService.getOptionsFromArgs(args);
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

}