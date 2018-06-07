import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.reflect.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.GroupLayout.Alignment.TRAILING;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Memory class for simulating OS memory management.
 * 
 * @version 1.0 May 2018
 * @author Amanda Camacho, <ajmandi94@gmail.com> Benjamin Amos
 *         <benjamin.oxi@gmail.com>
 */

public class MemoryInterface extends JPanel {

    private Memory memory;
    // private Process process[];
    private List<Process> process;
    private int countProcess = 0;

    private JPanel allPanel;
    private JPanel panelFila1;
    private JPanel panelFila2;
    private JPanel panelFila3;

    private JPanel memoryConf;
    private JLabel memorySizeLabel;
    private JTextField memorySize;
    private JLabel memoryPageSizeLabel;
    private JTextField memoryPageSize;
    private JButton saveMemoryConf;

    private JPanel processConf;
    private JLabel processNameLabel;
    private JTextField processName;
    private JLabel processSizeLabel;
    private JTextField processSize;
    private JButton saveProcessConf;

    private JPanel statisticsPanel;
    private JLabel labelMemory1;
    private JLabel labelMemory2;
    private JLabel labelAvailableMemory1;
    private JLabel labelAvailableMemory2;
    private JLabel labelUsedMemory1;
    private JLabel labelUsedMemory2;
    private JLabel labelNumberProcess1;
    private JLabel labelNumberProcess2;
    private JLabel labelUsedPages1;
    private JLabel labelUsedPages2;
    private JLabel labelPagesSize1;
    private JLabel labelPagesSize2;

    private JPanel panelProcessList;
    private JTable processTable;
    private JScrollPane processScroll;

    private JPanel panelProcessStatus;
    private JButton suspendProcess;
    private JButton deleteProcess;
    private JButton readyProcess;
    private JButton lockProcess;

    private JPanel panelMemoryStatus;
    private JTable memoryTable;
    private JScrollPane memoryScroll;

    private JPanel graphicsPanel;
    private JTextArea alertArea;
    private JScrollPane scrollAlertArea;

    /**
     * Constructor for memory panel
     * 
     */

    private JPanel crearPanelMemoria() {

        /* PANEL DE CONFIGURACION DE MEMORIA */

        String titulo = "Configuracion de Memoria ";

        titulo = titulo.substring(titulo.lastIndexOf('.') + 1);
        memoryConf.setBorder(new TitledBorder(titulo));
        memoryConf.setPreferredSize(new Dimension(300, 200));

        memorySizeLabel.setText("Tamaño de memoria ");
        memorySizeLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        memorySizeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        memoryPageSizeLabel.setText("Tamaño de paginas");
        memoryPageSizeLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        memoryPageSizeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        memorySize.setText("15360");
        memorySize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memorySizeActionPerformed(evt);
            }
        });
        memoryPageSize.setText("1024");
        memoryPageSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memoryPageSizeActionPerformed(evt);
            }
        });

        saveMemoryConf.setText("Crear Memoria");
        saveMemoryConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMemoryConfActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(memoryConf);
        memoryConf.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().addComponent(memorySizeLabel).addComponent(memoryPageSizeLabel)
                .addComponent(saveMemoryConf));
        hGroup.addGroup(layout.createParallelGroup().addComponent(memorySize).addComponent(memoryPageSize));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(memorySizeLabel)
                .addComponent(memorySize));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(memoryPageSizeLabel)
                .addComponent(memoryPageSize));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(saveMemoryConf));
        layout.setVerticalGroup(vGroup);

        return (memoryConf);
    }

    /**
     * Constructor for process panel
     * 
     */

    private JPanel crearPanelProcesos() {

        /* PANEL DE CONFIGURACION DE PROCESOS */
        String tituloProceso = "Configuracion de Procesos ";

        tituloProceso = tituloProceso.substring(tituloProceso.lastIndexOf('.') + 1);
        processConf.setBorder(new TitledBorder(tituloProceso));
        processConf.setPreferredSize(new Dimension(300, 200));

        processNameLabel.setText("Nombre del Proceso");
        processNameLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        processNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        processSizeLabel.setText("Tamaño de Proceso (MB)");
        processSizeLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        processSizeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        processName.setText("Proceso1");

        processName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processNameActionPerformed(evt);
            }
        });

        processSize.setText("1024");
        processSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processSizeActionPerformed(evt);
            }
        });

        saveProcessConf.setText("Crear Proceso");
        // No se puede crear un proceso si la memoria no ha sido creada
        saveProcessConf.setEnabled(false);
        saveProcessConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveProcessConfAction(evt);
            }
        });

        GroupLayout layoutProcess = new GroupLayout(processConf);
        processConf.setLayout(layoutProcess);
        layoutProcess.setAutoCreateGaps(true);
        layoutProcess.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layoutProcess.createSequentialGroup();
        hGroup.addGroup(layoutProcess.createParallelGroup().addComponent(processNameLabel)
                .addComponent(processSizeLabel).addComponent(saveProcessConf));
        hGroup.addGroup(layoutProcess.createParallelGroup().addComponent(processName).addComponent(processSize));
        layoutProcess.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layoutProcess.createSequentialGroup();

        vGroup.addGroup(layoutProcess.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(processNameLabel)
                .addComponent(processName));
        vGroup.addGroup(layoutProcess.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(processSizeLabel)
                .addComponent(processSize));
        vGroup.addGroup(
                layoutProcess.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(saveProcessConf));
        layoutProcess.setVerticalGroup(vGroup);

        /* PANEL DE CONFIGURACION DE PROCESOS */

        return (processConf);
    }

    /**
     * Constructor for statistics panel
     * 
     */

    private JPanel crearPanelEstadisticas() {

        /* PANEL DE CONFIGURACION DE PROCESOS */
        String tituloEstadisticas = "Estadisticas ";

        tituloEstadisticas = tituloEstadisticas.substring(tituloEstadisticas.lastIndexOf('.') + 1);
        statisticsPanel.setBorder(new TitledBorder(tituloEstadisticas));
        statisticsPanel.setPreferredSize(new Dimension(300, 200));

        labelMemory1.setText("Tamaño de la memoria");

        labelMemory2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelMemory2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelMemory2.setText("-");
        labelAvailableMemory1.setText("Memoria Disponible");

        labelAvailableMemory2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelAvailableMemory2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelAvailableMemory2.setText("-");

        labelUsedMemory1.setText("Memoria Usada");

        labelUsedMemory2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelUsedMemory2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelUsedMemory2.setText("-");

        labelNumberProcess1.setText("Cantidad de procesos");

        labelNumberProcess2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNumberProcess2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelNumberProcess2.setText("-");

        labelUsedPages1.setText("Páginas en uso");

        labelUsedPages2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelUsedPages2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelUsedPages2.setText("-");

        labelPagesSize1.setText("Tamaño de página");

        labelPagesSize2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelPagesSize2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelPagesSize2.setText("-");

        GroupLayout layoutStatistics = new GroupLayout(statisticsPanel);
        statisticsPanel.setLayout(layoutStatistics);
        layoutStatistics.setAutoCreateGaps(true);
        layoutStatistics.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layoutStatistics.createSequentialGroup();
        hGroup.addGroup(layoutStatistics.createParallelGroup().addComponent(labelMemory1)
                .addComponent(labelAvailableMemory1).addComponent(labelUsedMemory1).addComponent(labelNumberProcess1)
                .addComponent(labelUsedPages1).addComponent(labelPagesSize1));
        hGroup.addGroup(layoutStatistics.createParallelGroup().addComponent(labelMemory2)
                .addComponent(labelAvailableMemory2).addComponent(labelUsedMemory2).addComponent(labelNumberProcess2)
                .addComponent(labelUsedPages2).addComponent(labelPagesSize2));
        layoutStatistics.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layoutStatistics.createSequentialGroup();

        vGroup.addGroup(layoutStatistics.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelMemory1)
                .addComponent(labelMemory2));
        vGroup.addGroup(layoutStatistics.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelAvailableMemory1).addComponent(labelAvailableMemory2));
        vGroup.addGroup(layoutStatistics.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelUsedMemory1).addComponent(labelUsedMemory2));
        vGroup.addGroup(layoutStatistics.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelNumberProcess1).addComponent(labelNumberProcess2));
        vGroup.addGroup(layoutStatistics.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelUsedPages1).addComponent(labelUsedPages2));
        vGroup.addGroup(layoutStatistics.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelPagesSize1).addComponent(labelPagesSize2));
        layoutStatistics.setVerticalGroup(vGroup);

        return (statisticsPanel);
    }

    /**
     * Constructor for Process List panel
     * 
     */

    private JPanel crearPanelListaProc() {

        /* PANEL DE LISTA DE PROCESOS */
        String processTitle = "Lista de Procesos ";

        processTitle = processTitle.substring(processTitle.lastIndexOf('.') + 1);
        panelProcessList.setBorder(new TitledBorder(processTitle));
        panelProcessList.setPreferredSize(new Dimension(700, 200));

        processTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

        }, new String[] { "PID", "Nombre", "Tamaño", "Páginas", "Pag. en memoria", "Estado" }) {
            boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        processTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                processTableFocusLost(evt);
            }
        });

        processTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                processTableMouseClicked(evt);
            }
        });

        processScroll.setViewportView(processTable);

        if (processTable.getColumnModel().getColumnCount() > 0) {
            processTable.getColumnModel().getColumn(0).setResizable(false);
            processTable.getColumnModel().getColumn(1).setResizable(false);
            processTable.getColumnModel().getColumn(2).setResizable(false);
            processTable.getColumnModel().getColumn(3).setResizable(false);
            processTable.getColumnModel().getColumn(4).setResizable(false);
            processTable.getColumnModel().getColumn(5).setResizable(false);
        }

        GroupLayout processTableLayout = new GroupLayout(panelProcessList);
        processTableLayout.setAutoCreateGaps(true);
        processTableLayout.setAutoCreateContainerGaps(true);
        panelProcessList.setLayout(processTableLayout);

        processTableLayout.setHorizontalGroup(processTableLayout.createSequentialGroup()
                .addGroup(processTableLayout.createParallelGroup(LEADING).addComponent(processScroll)));

        processTableLayout.setVerticalGroup(processTableLayout.createSequentialGroup()
                .addGroup(processTableLayout.createParallelGroup(BASELINE).addComponent(processScroll)));

        return (panelProcessList);
    }

    /**
     * Constructor for Process Status panel
     * 
     */

    private JPanel crearPanelEstadoProc() {
        /* PANEL DE ESTADOS DE PROCESOS */
        String processStatusTitle = "Estado de procesos ";

        processStatusTitle = processStatusTitle.substring(processStatusTitle.lastIndexOf('.') + 1);
        panelProcessStatus.setBorder(new TitledBorder(processStatusTitle));
        panelProcessStatus.setPreferredSize(new Dimension(200, 200));

        suspendProcess.setText("Suspender");
        suspendProcess.setEnabled(false);
        suspendProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suspendProcessActionPerformed(evt);
            }
        });
        deleteProcess.setText("Eliminar");
        deleteProcess.setEnabled(false);

        deleteProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProcessActioPerformed(evt);
            }
        });

        readyProcess.setText("Listo");
        readyProcess.setEnabled(false);

        readyProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readyProcessActionPerformed(evt);
            }
        });

        lockProcess.setText("Bloqueado");
        lockProcess.setEnabled(false);

        lockProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lockProcessActionPerformed(evt);
            }
        });

        GroupLayout processStatusLayout = new GroupLayout(panelProcessStatus);
        processStatusLayout.setAutoCreateGaps(true);
        processStatusLayout.setAutoCreateContainerGaps(true);
        panelProcessStatus.setLayout(processStatusLayout);

        processStatusLayout.setHorizontalGroup(processStatusLayout.createSequentialGroup()
                .addGroup(processStatusLayout.createParallelGroup(LEADING).addComponent(suspendProcess)
                        .addComponent(deleteProcess).addComponent(readyProcess).addComponent(lockProcess)));

        processStatusLayout.setVerticalGroup(processStatusLayout.createSequentialGroup()
                .addGroup(processStatusLayout.createParallelGroup(BASELINE).addComponent(suspendProcess))
                .addGroup(processStatusLayout.createParallelGroup(BASELINE).addComponent(deleteProcess))
                .addGroup(processStatusLayout.createParallelGroup(BASELINE).addComponent(readyProcess))
                .addGroup(processStatusLayout.createParallelGroup(BASELINE).addComponent(lockProcess)));

        return (panelProcessStatus);
    }

    /**
     * Constructor for memory list panel
     * 
     */

    private JPanel crearPanelListaMemory() {

        /* PANEL DE ESTADOS DE PROCESOS */
        String memoryStatusTitle = "Estado de la Memoria ";

        memoryStatusTitle = memoryStatusTitle.substring(memoryStatusTitle.lastIndexOf('.') + 1);
        panelMemoryStatus.setBorder(new TitledBorder(memoryStatusTitle));
        panelMemoryStatus.setPreferredSize(new Dimension(600, 200));

        memoryTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

        }, new String[] { "Direccion Fisica ", "ID pagina", "PID", "Nombre Proceso", "Num. Paginas" }) {
            boolean[] canEdit = new boolean[] { false, false, false, false, false };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        memoryScroll.setViewportView(memoryTable);

        if (memoryTable.getColumnModel().getColumnCount() > 0) {
            memoryTable.getColumnModel().getColumn(0).setResizable(false);
            memoryTable.getColumnModel().getColumn(1).setResizable(false);
            memoryTable.getColumnModel().getColumn(2).setResizable(false);
            memoryTable.getColumnModel().getColumn(3).setResizable(false);
            memoryTable.getColumnModel().getColumn(4).setResizable(false);
        }

        GroupLayout memoryTableLayout = new GroupLayout(panelMemoryStatus);
        memoryTableLayout.setAutoCreateGaps(true);
        memoryTableLayout.setAutoCreateContainerGaps(true);
        panelMemoryStatus.setLayout(memoryTableLayout);

        memoryTableLayout.setHorizontalGroup(memoryTableLayout.createSequentialGroup()
                .addGroup(memoryTableLayout.createParallelGroup(LEADING).addComponent(memoryScroll)));

        memoryTableLayout.setVerticalGroup(memoryTableLayout.createSequentialGroup()
                .addGroup(memoryTableLayout.createParallelGroup(BASELINE).addComponent(memoryScroll)));

        return (panelMemoryStatus);
    }

    /**
     * Constructor for graphics panel
     * 
     */

    private JPanel graphics() {

        /* PANEL DE ESTADOS DE PROCESOS */
        String graphicsTitle = "Gráficos de estado ";

        graphicsTitle = graphicsTitle.substring(graphicsTitle.lastIndexOf('.') + 1);
        graphicsPanel.setBorder(new TitledBorder(graphicsTitle));
        graphicsPanel.setPreferredSize(new Dimension(300, 200));

        alertArea.setColumns(25);
        alertArea.setRows(11);
        scrollAlertArea.setViewportView(alertArea);

        graphicsPanel.add(scrollAlertArea);

        return (graphicsPanel);
    }

    /**
     * Constructor for all panels
     * 
     */

    public MemoryInterface() {

        memoryConf = new JPanel();
        memorySizeLabel = new JLabel();
        memorySize = new JTextField();
        memoryPageSizeLabel = new JLabel();
        memoryPageSize = new JTextField();
        saveMemoryConf = new JButton();

        processConf = new JPanel();
        processNameLabel = new JLabel();
        processName = new JTextField();
        processSizeLabel = new JLabel();
        processSize = new JTextField();
        saveProcessConf = new JButton();

        statisticsPanel = new JPanel();
        labelMemory1 = new JLabel();
        labelMemory2 = new JLabel();
        labelAvailableMemory1 = new JLabel();
        labelAvailableMemory2 = new JLabel();
        labelUsedMemory1 = new JLabel();
        labelUsedMemory2 = new JLabel();
        labelNumberProcess1 = new JLabel();
        labelNumberProcess2 = new JLabel();
        labelUsedPages1 = new JLabel();
        labelUsedPages2 = new JLabel();
        labelPagesSize1 = new JLabel();
        labelPagesSize2 = new JLabel();

        panelProcessList = new JPanel();
        processTable = new JTable();
        processScroll = new javax.swing.JScrollPane();

        panelProcessStatus = new JPanel();
        suspendProcess = new JButton();
        deleteProcess = new JButton();
        readyProcess = new JButton();
        lockProcess = new JButton();

        panelMemoryStatus = new JPanel();
        memoryTable = new JTable();
        memoryScroll = new javax.swing.JScrollPane();

        graphicsPanel = new JPanel();
        alertArea = new JTextArea();
        scrollAlertArea = new javax.swing.JScrollPane();

        JPanel allPanel = new JPanel();
        /*
         * String allPanelTitle = " Panel completo " ; allPanelTitle =
         * allPanelTitle.substring( allPanelTitle.lastIndexOf('.')+1 );
         * allPanel.setBorder( new TitledBorder( allPanelTitle ) );
         */
        JPanel panelFila1 = new JPanel();
        /*
         * String tituloFila1 = "Fila 1 " ; tituloFila1 = tituloFila1.substring(
         * tituloFila1.lastIndexOf('.')+1 ); panelFila1.setBorder( new TitledBorder(
         * tituloFila1 ) );
         */

        panelFila1.add(crearPanelMemoria());
        panelFila1.add(crearPanelProcesos());
        panelFila1.add(crearPanelEstadisticas());

        JPanel panelFila2 = new JPanel();
        /*
         * String tituloFila2 = "Fila 2 " ; tituloFila2 = tituloFila2.substring(
         * tituloFila2.lastIndexOf('.')+1 ); panelFila2.setBorder( new TitledBorder(
         * tituloFila2 ) );
         */
        panelFila2.add(crearPanelListaProc());
        panelFila2.add(crearPanelEstadoProc());

        JPanel panelFila3 = new JPanel();
        /*
         * String tituloFila3 = "Fila 3 " ; tituloFila3 = tituloFila3.substring(
         * tituloFila3.lastIndexOf('.')+1 ); panelFila3.setBorder( new TitledBorder(
         * tituloFila3 ) );
         */
        panelFila3.add(crearPanelListaMemory());
        panelFila3.add(graphics());

        GroupLayout groupLayout = new GroupLayout(allPanel);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        allPanel.setLayout(groupLayout);

        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(LEADING)
                        .addComponent(panelFila1).addComponent(panelFila2).addComponent(panelFila3)));

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(BASELINE).addComponent(panelFila1))
                .addGroup(groupLayout.createParallelGroup(BASELINE).addComponent(panelFila2))
                .addGroup(groupLayout.createParallelGroup(BASELINE).addComponent(panelFila3)));

        add(allPanel);

    }

    /**
     * Function for save memory botton action
     * 
     */

    private void saveMemoryConfActionPerformed(java.awt.event.ActionEvent evt) {
        int sizeMem = Integer.parseInt(memorySize.getText());
        int sizePag = Integer.parseInt(memoryPageSize.getText());
        System.out.println(sizeMem);
        if ((sizeMem % 2) == 0 && (sizePag % 2) == 0) {
            if (sizeMem > sizePag) {

                int pages = sizeMem / sizePag;
                memory = new Memory(sizeMem, pages, sizePag);
                this.process = new ArrayList<Process>(); //new Process[memory.getPageNumber()];
                alertArea.append("Se ha creado la memoria\n");
                saveMemoryConf.setEnabled(false);
                labelMemory2.setText(Integer.toString(sizeMem));
                labelAvailableMemory2.setText(Integer.toString(sizeMem));
                labelPagesSize2.setText(Integer.toString(sizePag));
                labelUsedMemory2.setText("0");
                labelNumberProcess2.setText("0");
                labelUsedPages2.setText("0");
                updateMemoryTable();
                saveProcessConf.setEnabled(true);

            } else {
                alertArea.append("Tamaño de pagina mayor al de la memoria \n ");
            }
        } else {
            alertArea.append("Tamaño de la memoria o de la pagina\n debe ser multiplo de \n");
        }
    }

    /**
     * Function
     * 
     */

    private void memorySizeActionPerformed(java.awt.event.ActionEvent evt) {

    }

    /**
     * Function
     * 
     */
    private void memoryPageSizeActionPerformed(java.awt.event.ActionEvent evt) {

    }

    /**
     * Function for save process botton action
     * 
     */

    private void saveProcessConfAction(java.awt.event.ActionEvent evt) {
        String nameProc = processName.getText();
        int sizeProc = Integer.parseInt(processSize.getText());
        System.out.println(nameProc);
        if (sizeProc <= memory.getfreeMemory()) {
            // creo el proceso
            // this.process.get(countProcess) = new Process(nameProc, sizeProc, memory.getPageSize());
            this.process.add(new Process(nameProc, sizeProc, memory.getPageSize()));
            // Lo agrego a memoria
            // memory.addProcess(this.process[countProcess]);
            memory.addProcess(this.process.get(countProcess));
            // no tengo funcion para esto todavia
            // ahora si
            alertArea.append(" Se ha creado el proceso " + nameProc + ", con PID "
                    // + Integer.toString(this.process[countProcess].getPid()) + "\n");
                    + Integer.toString(this.process.get(countProcess).getPid()) + "\n");
            countProcess++;
            labelNumberProcess2.setText(Integer.toString(countProcess));
            updateProcessTable();
            update();
        } else {
            alertArea.append("No hay suficiente espacio en la memoria para ejecutar el proceso");
        }

    }

    /**
     * Function
     * 
     */
    private void processSizeActionPerformed(java.awt.event.ActionEvent evt) {

    }

    /**
     * Function
     */

    private void processNameActionPerformed(java.awt.event.ActionEvent evt) {

    }
    // #########################################################################
    // #########################################################################

    private void processTableFocusLost(java.awt.event.FocusEvent evt) {

    }

    private void processTableMouseClicked(java.awt.event.MouseEvent evt) {
        int row = processTable.getSelectedRow();
        int selectedPid = Integer.parseInt(processTable.getModel().getValueAt(row, 0).toString());
        alertArea.append(Integer.toString(row) + "\n");
        // String status = process[row].status();
        String status = process.get(row).status();
        alertArea.append(status + "\n");
        deleteProcess.setEnabled(true);
        if (status == "Listo") {
            readyProcess.setEnabled(false);
            suspendProcess.setEnabled(false);
            lockProcess.setEnabled(true);
        } else if (status == "Bloqueado/Listo") {
            suspendProcess.setEnabled(false);
            readyProcess.setEnabled(true);
            lockProcess.setEnabled(false);
        } else if (status == "Bloqueado") {
            lockProcess.setEnabled(false);
            suspendProcess.setEnabled(false);
            readyProcess.setEnabled(true);
        } else if (status == "Eliminado") {
            suspendProcess.setEnabled(false);
            readyProcess.setEnabled(false);
            deleteProcess.setEnabled(false);
            lockProcess.setEnabled(false);
        }
    }

    // #########################################################################
    // #########################################################################

    private void suspendProcessActionPerformed(java.awt.event.ActionEvent evt) {
        int row = processTable.getSelectedRow();
        int selectedPid = Integer.parseInt(processTable.getModel().getValueAt(row, 0).toString());
        int status = 3;
        // se debe parar el tiempo de ejecucion
        for (Process pro : this.process) {
            if (pro.getPid() == selectedPid) {
                pro.setStatus(status);
            }
        }
        updateProcessTable();
    }

    private void deleteProcessActioPerformed(java.awt.event.ActionEvent evt) {
        int row = processTable.getSelectedRow();
        int selectedPid = Integer.parseInt(processTable.getModel().getValueAt(row, 0).toString());

        boolean wasInMemory = this.memory.killProcess(selectedPid);
        int status = 0;

        for (Process pro : this.process) {
            if (pro.getPid() == selectedPid) {
                pro.setStatus(status);
                this.process.remove(pro);
                break;
            }
        }

        DefaultTableModel model = (DefaultTableModel) processTable.getModel();
        model.removeRow(row);

        if (wasInMemory) {
            countProcess--;
            labelNumberProcess2.setText(Integer.toString(countProcess));
        }
        updateProcessTable();
        update();
        
    }

    private void readyProcessActionPerformed(java.awt.event.ActionEvent evt) {
        int row = processTable.getSelectedRow();
        int selectedPid = Integer.parseInt(processTable.getModel().getValueAt(row, 0).toString());

        int status = 1;
        // se debe colocar en la cola de listos
        for (Process pro : this.process) {
            if (pro.getPid() == selectedPid) {
                pro.setStatus(status);
            }
        }
        updateProcessTable();
    }

    private void lockProcessActionPerformed(java.awt.event.ActionEvent evt) {
        int row = processTable.getSelectedRow();
        int selectedPid = Integer.parseInt(processTable.getModel().getValueAt(row, 0).toString());
        
        int status = 2;
        // se debe parar el tiempo de ejecucion
        for (Process pro : this.process) {
            if (pro.getPid() == selectedPid) {
                pro.setStatus(status);
            }
        }
        updateProcessTable();
    
    }

    // ########################################################################
    // ########################################################################

    private void updateMemoryTable() {

        // Elimino todas las filas
        DefaultTableModel model = (DefaultTableModel) memoryTable.getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        // Vuelvo a cargar desde lo que me dice la memoria que tiene
    }

    private void updateProcessTable() {

        // Elimino todas las filas
        DefaultTableModel model = (DefaultTableModel) processTable.getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        // Vuelvo a cargar desde la lista de procesos que tengo global
        
        // for (int i = 0; i < countProcess; i++) {
        //     // Object[] row = {process[i].getPid(), process[i].getName(),
        //     // process[i].getSize(),"paginas", "PaginasMem",process[i].status()};
        //     Object[] row = { process[i].getPid(), process[i].getName(), process[i].getSize(),
        //             process[i].getNumberOfPages(), process[i].printPages(), process[i].status() };
        //     model.addRow(row);
        // }
        for (Process pro : process) {
            Object[] row = { pro.getPid(), pro.getName(), pro.getSize(),
                    pro.getNumberOfPages(), pro.printPages(), pro.status() };
            model.addRow(row);
        }
    }

    private void update() {
        labelUsedMemory2.setText(Integer.toString(memory.getUsedSpace()));
        labelAvailableMemory2.setText(Integer.toString(memory.getfreeMemory()));
        labelUsedPages2.setText(Integer.toString(memory.getUsedPages()));
        updateProcessTable();
    }

    /**
     * 
     * 
     */

    public static void main(String args[]) {
        JFrame frame = new JFrame("Simulador de memoria");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
        frame.getContentPane().add(new MemoryInterface(), BorderLayout.WEST);
        frame.setSize(950, 700);
        frame.setVisible(true);
    }
}