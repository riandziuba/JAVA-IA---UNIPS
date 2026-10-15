package br.com.unipds.ebook;

import br.com.unipds.EBookInfoDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

@ApplicationScoped
public class EbookPropertiesReaderFile implements IEbookPropertiesReader {

    public EBookInfoDTO read(Path sourceDir) {
        Path propertiesFile = sourceDir.resolve("ebook.properties");

        if(!Files.exists(propertiesFile)) {
            throw new IllegalStateException("O arquivo ebook.properties não encontrado em:" + sourceDir);
        }

        Properties properties = new Properties();
        try (Reader reader = Files.newBufferedReader(propertiesFile)) {
            properties.load(reader);
            String propertyTitle = "title";
            String propertyAuthor = "author";
            String title = properties.getProperty(propertyTitle);
            validateProperty(title, propertyTitle);
            String author = properties.getProperty(propertyAuthor);
            validateProperty(author, propertyTitle);
            return new EBookInfoDTO(title, author);
        } catch (IOException e) {
            throw new IllegalStateException("Erro ao ler o arquivo: " + propertiesFile);
        }
    }

    private static void validateProperty(String property, String propertyKey) {
        if(property == null || property.isBlank()) {
            throw new IllegalStateException("Propriedade Invalida: " + propertyKey);
        }
    }
}
