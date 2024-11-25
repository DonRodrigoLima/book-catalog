package com.rodrigolima.bookcatalog.service;

import net.sf.jasperreports.engine.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;

class RelatorioServiceTest {

    @InjectMocks
    private RelatorioService relatorioService;

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Configuração do mock do DataSource
        when(dataSource.getConnection()).thenReturn(connection);

        // Configuração do serviço com o mock do DataSource
        relatorioService = new RelatorioService(dataSource);
    }

    @Test
    void deveGerarRelatorioLivros() throws Exception {
        // Arrange
        InputStream relatorioStream = getClass().getResourceAsStream("/reports/RelatorioLivros.jasper");
        assertNotNull(relatorioStream, "O arquivo RelatorioLivros.jasper não foi encontrado.");

        JasperPrint mockJasperPrint = mock(JasperPrint.class);
        byte[] mockPdf = new byte[]{1, 2, 3};

         try (MockedStatic<JasperFillManager> mockedFillManager = mockStatic(JasperFillManager.class);
             MockedStatic<JasperExportManager> mockedExportManager = mockStatic(JasperExportManager.class)) {

            // Mock do preenchimento do relatório
            mockedFillManager.when(() -> JasperFillManager.fillReport(any(InputStream.class), anyMap(), eq(connection)))
                    .thenReturn(mockJasperPrint);

            // Mock da exportação para PDF
            mockedExportManager.when(() -> JasperExportManager.exportReportToPdf(mockJasperPrint))
                    .thenReturn(mockPdf);

            // Act
            byte[] pdf = relatorioService.gerarRelatorioLivros();

            // Assert
            assertNotNull(pdf, "O relatório PDF não foi gerado.");
            assertEquals(3, pdf.length, "O tamanho do PDF gerado não corresponde ao esperado.");

            // Verificações de chamadas
            verify(dataSource, times(1)).getConnection();
            mockedFillManager.verify(() -> JasperFillManager.fillReport(any(InputStream.class), anyMap(), eq(connection)));
            mockedExportManager.verify(() -> JasperExportManager.exportReportToPdf(mockJasperPrint));
        }
    }
}
