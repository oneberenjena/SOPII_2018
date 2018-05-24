import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.reflect.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import static javax.swing.GroupLayout.Alignment.TRAILING;

public class MemoryInterface extends JPanel {
  
  
  static JPanel crearPanelMemoria() {

    /*  PANEL DE CONFIGURACION DE MEMORIA */
    JPanel panelMemoria = new JPanel();
    String titulo = "Configuracion de Memoria " ;
    
    titulo = titulo.substring( titulo.lastIndexOf('.')+1 );
    panelMemoria.setBorder( new TitledBorder( titulo ) );
    panelMemoria.setPreferredSize(new Dimension(300, 200));
    

    JPanel memoryConf = new JPanel();
    JLabel memorySizeLabel= new JLabel();
    JTextField memorySize = new JTextField();
    JLabel memoryPageSizeLabel= new JLabel();
    JTextField memoryPageSize = new JTextField();
    JButton saveMemoryConf = new JButton();

    memorySizeLabel.setText("Tamaño de memoria ");
    memorySizeLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); 
    memorySizeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);


    memoryPageSizeLabel.setText("Tamaño de paginas"); 
    memoryPageSizeLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); 
    memoryPageSizeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);


    memorySize.setText("20480");
    //Aqui va un listen

    memoryPageSize.setText("51200");
    //Aqui va otro 

    saveMemoryConf.setText("Crear Memoria");
    /*saveMemoryConf.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            // Aqui el metodo que ejecuta la accion
        }
    });*/


    GroupLayout layout = new GroupLayout(panelMemoria);
    panelMemoria.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);



    GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
    hGroup.addGroup(layout.createParallelGroup().
            addComponent(memorySizeLabel).addComponent(memoryPageSizeLabel).addComponent(saveMemoryConf));
    hGroup.addGroup(layout.createParallelGroup().
            addComponent(memorySize).addComponent(memoryPageSize));
    layout.setHorizontalGroup(hGroup);


    GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

    vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(memorySizeLabel).addComponent(memorySize));
    vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(memoryPageSizeLabel).addComponent(memoryPageSize));
    vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(saveMemoryConf));
    layout.setVerticalGroup(vGroup);

    


    return(panelMemoria);
  }



  static JPanel crearPanelProcesos() {

    /*  PANEL DE CONFIGURACION DE PROCESOS*/
    JPanel panelProcesos = new JPanel();
    String tituloProceso = "Configuracion de Procesos " ;
    
    tituloProceso = tituloProceso.substring( tituloProceso.lastIndexOf('.')+1 );
    panelProcesos.setBorder( new TitledBorder( tituloProceso ) );
    panelProcesos.setPreferredSize(new Dimension(300, 200));
    

    JPanel processConf = new JPanel();
    JLabel processNameLabel= new JLabel();
    JTextField processName = new JTextField();
    JLabel processSizeLabel= new JLabel();
    JTextField processSize = new JTextField();
    JButton saveProcessConf = new JButton();

    processNameLabel.setText("Nombre del Proceso");
    processNameLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); 
    processNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);


    processSizeLabel.setText("Tamaño de Proceso"); 
    processSizeLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); 
    processSizeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);


    processName.setText("20480");
    //Aqui va un listen

    processSize.setText("51200");
    //Aqui va otro 

    saveProcessConf.setText("Crear Proceso");

    /*saveProcessConf.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            // Aqui el metodo que ejecuta la accion
        }
    });*/



    GroupLayout layoutProcess = new GroupLayout(panelProcesos);
    panelProcesos.setLayout(layoutProcess);
    layoutProcess.setAutoCreateGaps(true);
    layoutProcess.setAutoCreateContainerGaps(true);



    GroupLayout.SequentialGroup hGroup = layoutProcess.createSequentialGroup();
    hGroup.addGroup(layoutProcess.createParallelGroup().
            addComponent(processNameLabel).addComponent(processSizeLabel).addComponent(saveProcessConf));
    hGroup.addGroup(layoutProcess.createParallelGroup().
            addComponent(processName).addComponent(processSize));
    layoutProcess.setHorizontalGroup(hGroup);


    GroupLayout.SequentialGroup vGroup = layoutProcess.createSequentialGroup();

    vGroup.addGroup(layoutProcess.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(processNameLabel).addComponent(processName));
    vGroup.addGroup(layoutProcess.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(processSizeLabel).addComponent(processSize));
    vGroup.addGroup(layoutProcess.createParallelGroup(GroupLayout.Alignment.BASELINE).
        addComponent(saveProcessConf));
    layoutProcess.setVerticalGroup(vGroup);

    /*  PANEL DE CONFIGURACION DE PROCESOS*/


    return(panelProcesos);
  }


   static JPanel crearPanelEstadisticas() {

    /*  PANEL DE CONFIGURACION DE PROCESOS*/
    JPanel panelEstadisticas = new JPanel();
    String tituloEstadisticas = "Estadisticas " ;
    
    tituloEstadisticas = tituloEstadisticas.substring( tituloEstadisticas.lastIndexOf('.')+1 );
    panelEstadisticas.setBorder( new TitledBorder( tituloEstadisticas ) );
    panelEstadisticas.setPreferredSize(new Dimension(300, 200));

    JLabel labelMemory1 = new JLabel();
    JLabel labelMemory2 = new JLabel();

    JLabel labelAvailableMemory1 = new JLabel();
    JLabel labelAvailableMemory2 = new JLabel();

    JLabel labelUsedMemory1 = new JLabel();
    JLabel labelUsedMemory2 = new JLabel();

    JLabel labelNumberProcess1 = new JLabel();
    JLabel labelNumberProcess2 = new JLabel();

    JLabel labelUsedPages1 = new JLabel();
    JLabel labelUsedPages2 = new JLabel();

    JLabel labelPagesSize1 = new JLabel();
    JLabel labelPagesSize2 = new JLabel();


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


    GroupLayout layoutStatistics = new GroupLayout(panelEstadisticas);
    panelEstadisticas.setLayout(layoutStatistics);
    layoutStatistics.setAutoCreateGaps(true);
    layoutStatistics.setAutoCreateContainerGaps(true);


    GroupLayout.SequentialGroup hGroup = layoutStatistics.createSequentialGroup();
    hGroup.addGroup(layoutStatistics.createParallelGroup().
            addComponent(labelMemory1).addComponent(labelAvailableMemory1).addComponent(labelUsedMemory1)
            .addComponent(labelNumberProcess1).addComponent(labelUsedPages1).addComponent(labelPagesSize1));
    hGroup.addGroup(layoutStatistics.createParallelGroup().
            addComponent(labelMemory2).addComponent(labelAvailableMemory2).addComponent(labelUsedMemory2)
            .addComponent(labelNumberProcess2).addComponent(labelUsedPages2).addComponent(labelPagesSize2));
    layoutStatistics.setHorizontalGroup(hGroup);


    GroupLayout.SequentialGroup vGroup = layoutStatistics.createSequentialGroup();

    vGroup.addGroup(layoutStatistics.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(labelMemory1).addComponent(labelMemory2));
    vGroup.addGroup(layoutStatistics.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(labelAvailableMemory1).addComponent(labelAvailableMemory2));
    vGroup.addGroup(layoutStatistics.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(labelUsedMemory1).addComponent(labelUsedMemory2));
    vGroup.addGroup(layoutStatistics.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(labelNumberProcess1).addComponent(labelNumberProcess2));
    vGroup.addGroup(layoutStatistics.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(labelUsedPages1).addComponent(labelUsedPages2));
    vGroup.addGroup(layoutStatistics.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(labelPagesSize1).addComponent(labelPagesSize2));
    layoutStatistics.setVerticalGroup(vGroup);
    

    return(panelEstadisticas);
  }


  static JPanel crearPanelListaProc() {

    /*  PANEL DE LISTA DE PROCESOS*/
    JPanel panelProcessList = new JPanel();
    String processTitle = "Lista de Procesos " ;
    
    processTitle = processTitle.substring( processTitle.lastIndexOf('.')+1 );
    panelProcessList.setBorder( new TitledBorder( processTitle ) );
    panelProcessList.setPreferredSize(new Dimension(700, 200));

    return(panelProcessList);
  }

  static JPanel crearPanelEstadoProc() {

    /*  PANEL DE  ESTADOS DE PROCESOS*/
    JPanel panelProcessStatus = new JPanel();
    String processStatusTitle = "Estado de procesos " ;
    
    processStatusTitle = processStatusTitle.substring( processStatusTitle.lastIndexOf('.')+1 );
    panelProcessStatus.setBorder( new TitledBorder( processStatusTitle ) );
    panelProcessStatus.setPreferredSize(new Dimension(200, 200));
    JButton suspendProcess = new JButton();
    JButton deleteProcess = new JButton();
    JButton readyProcess = new JButton();
    JButton lockProcess = new JButton();


    suspendProcess.setText("Suspender");
    suspendProcess.setEnabled(false);
    /*suspendProcess.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            // Aqui el metodo que ejecuta la accion
        }
    });*/ 
    deleteProcess.setText("Eliminar");
    deleteProcess.setEnabled(false);

    /*deleteProcess.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            // Aqui el metodo que ejecuta la accion
        }
    });*/ 
    
    readyProcess.setText("Listo");
    readyProcess.setEnabled(false);

    /*readyProcess.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            // Aqui el metodo que ejecuta la accion
        }
    });*/ 
    
    lockProcess.setText("Bloqueado");
    lockProcess.setEnabled(false);

    /*lockProcess.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            // Aqui el metodo que ejecuta la accion
        }
    });*/

    GroupLayout processStatusLayout = new GroupLayout(panelProcessStatus); 
    processStatusLayout.setAutoCreateGaps(true);
    processStatusLayout.setAutoCreateContainerGaps(true);
    panelProcessStatus.setLayout(processStatusLayout);


    processStatusLayout.setHorizontalGroup(processStatusLayout
        .createSequentialGroup()
        .addGroup(
            processStatusLayout.createParallelGroup(LEADING).addComponent(suspendProcess)
                .addComponent(deleteProcess)
                .addComponent(readyProcess)
                .addComponent(lockProcess)));

    processStatusLayout.setVerticalGroup(processStatusLayout
        .createSequentialGroup()
        .addGroup(
            processStatusLayout.createParallelGroup(BASELINE).addComponent(suspendProcess))
        .addGroup(
            processStatusLayout.createParallelGroup(BASELINE).addComponent(deleteProcess))
        .addGroup(
            processStatusLayout.createParallelGroup(BASELINE).addComponent(readyProcess))
        .addGroup(
            processStatusLayout.createParallelGroup(BASELINE).addComponent(lockProcess)));

    
    return(panelProcessStatus);
  }

  static JPanel crearPanelListaMemory() {

    /*  PANEL DE  ESTADOS DE PROCESOS*/
    JPanel panelMemoryStatus = new JPanel();
    String memoryStatusTitle = "Estado de la Memoria " ;
    
    memoryStatusTitle = memoryStatusTitle.substring( memoryStatusTitle.lastIndexOf('.')+1 );
    panelMemoryStatus.setBorder( new TitledBorder( memoryStatusTitle ) );
    panelMemoryStatus.setPreferredSize(new Dimension(700, 200));
    
    return(panelMemoryStatus);
  }

   static JPanel graphics() {

    /*  PANEL DE  ESTADOS DE PROCESOS*/
    JPanel graphicsPanel = new JPanel();
    String graphicsTitle = "Gráficos de estado " ;
    
    graphicsTitle = graphicsTitle.substring( graphicsTitle.lastIndexOf('.')+1 );
    graphicsPanel.setBorder( new TitledBorder( graphicsTitle ) );
    graphicsPanel.setPreferredSize(new Dimension(200, 200));
    
    return(graphicsPanel);
  }


  
  public MemoryInterface() {

    JPanel allPanel = new JPanel();
    /*String allPanelTitle = " Panel completo " ;
    allPanelTitle = allPanelTitle.substring( allPanelTitle.lastIndexOf('.')+1 );
    allPanel.setBorder( new TitledBorder( allPanelTitle ) );
    */
    JPanel panelFila1 = new JPanel();
    /*String tituloFila1 = "Fila 1 " ;
    tituloFila1 = tituloFila1.substring( tituloFila1.lastIndexOf('.')+1 );
    panelFila1.setBorder( new TitledBorder( tituloFila1 ) );
    */

    panelFila1.add( crearPanelMemoria() );
    panelFila1.add( crearPanelProcesos() );
    panelFila1.add( crearPanelEstadisticas() );

    JPanel panelFila2 = new JPanel();
    /*String tituloFila2 = "Fila 2 " ;
    tituloFila2 = tituloFila2.substring( tituloFila2.lastIndexOf('.')+1 );
    panelFila2.setBorder( new TitledBorder( tituloFila2 ) );
    */
    panelFila2.add(crearPanelListaProc());
    panelFila2.add(crearPanelEstadoProc());

    JPanel panelFila3 = new JPanel();
    /*String tituloFila3 = "Fila 3 " ;
    tituloFila3 = tituloFila3.substring( tituloFila3.lastIndexOf('.')+1 );
    panelFila3.setBorder( new TitledBorder( tituloFila3 ) );
    */
    panelFila3.add(crearPanelListaMemory());
    panelFila3.add(graphics());




    GroupLayout groupLayout = 
    new GroupLayout(allPanel); 
    groupLayout.setAutoCreateGaps(true);
    groupLayout.setAutoCreateContainerGaps(true);
    allPanel.setLayout(groupLayout);


    groupLayout.setHorizontalGroup(groupLayout
        .createSequentialGroup()
        .addGroup(
            groupLayout.createParallelGroup(LEADING).addComponent(panelFila1)
                .addComponent(panelFila2)
                .addComponent(panelFila3)));

    groupLayout.setVerticalGroup(groupLayout
        .createSequentialGroup()
        .addGroup(
            groupLayout.createParallelGroup(BASELINE).addComponent(panelFila1))
        .addGroup(
            groupLayout.createParallelGroup(BASELINE).addComponent(panelFila2))
        .addGroup(
            groupLayout.createParallelGroup(BASELINE).addComponent(panelFila3)));

    add(allPanel);
   
  }
  
  public static void main( String args[] ) {
    JFrame frame = new JFrame( "Simulador de memoria" );
    frame.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
      }
    });
    frame.getContentPane().add( new MemoryInterface(),BorderLayout.WEST );
    frame.setSize( 950,680 );
    frame.setVisible( true );
  }
}