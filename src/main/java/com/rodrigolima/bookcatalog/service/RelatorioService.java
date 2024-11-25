package com.rodrigolima.bookcatalog.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperExportManager;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class RelatorioService {

    private final DataSource dataSource;

    public RelatorioService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public byte[] gerarRelatorioLivros() throws JRException {
        InputStream relatorioStream = getClass().getResourceAsStream("/reports/RelatorioLivros.jasper");
        if (relatorioStream == null) {
            throw new RuntimeException("Arquivo RelatorioLivros.jasper não encontrado no caminho especificado!");
        }

        Map<String, Object> parametros = new HashMap<>();

        try (var connection = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(relatorioStream, parametros, connection);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter conexão com o banco de dados: " + e.getMessage(), e);
        }
    }
}
