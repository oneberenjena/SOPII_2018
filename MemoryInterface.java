import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.reflect.*;

public class MemoryInterface extends JPanel {
  
;

  static String ids[] = { 
    "Mortadelo","Filemon","Carpanta", 
    "Rompetechos","Pepe Gotera","Otilio",
  };
  
  static JPanel crearPanelMemoria() {

    /*  PANEL DE CONFIGURACION DE MEMORIA */
    JPanel panelMemoria = new JPanel();
    String titulo = "Configuracion de Memoria " ;
    
    titulo = titulo.substring( titulo.lastIndexOf('.')+1 );
    panelMemoria.setBorder( new TitledBorder( titulo ) );
    panelMemoria.setPreferredSize(new Dimension(250, 200));
    

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

    GroupLayout layout = new GroupLayout(panelMemoria);
    panelMemoria.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);



    GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
    hGroup.addGroup(layout.createParallelGroup().
            addComponent(memorySizeLabel).addComponent(memoryPageSizeLabel));
    hGroup.addGroup(layout.createParallelGroup().
            addComponent(memorySize).addComponent(memoryPageSize));
    layout.setHorizontalGroup(hGroup);


    GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

    vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(memorySizeLabel).addComponent(memorySize));
    vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(memoryPageSizeLabel).addComponent(memoryPageSize));
    layout.setVerticalGroup(vGroup);

    


    return(panelMemoria);
  }



  static JPanel crearPanelProcesos() {

    /*  PANEL DE CONFIGURACION DE PROCESOS*/
    JPanel panelProcesos = new JPanel();
    String tituloProceso = "Configuracion de Procesos " ;
    
    tituloProceso = tituloProceso.substring( tituloProceso.lastIndexOf('.')+1 );
    panelProcesos.setBorder( new TitledBorder( tituloProceso ) );
    panelProcesos.setPreferredSize(new Dimension(250, 200));
    

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

    GroupLayout layoutProcess = new GroupLayout(panelProcesos);
    panelProcesos.setLayout(layoutProcess);
    layoutProcess.setAutoCreateGaps(true);
    layoutProcess.setAutoCreateContainerGaps(true);



    GroupLayout.SequentialGroup hGroup = layoutProcess.createSequentialGroup();
    hGroup.addGroup(layoutProcess.createParallelGroup().
            addComponent(processNameLabel).addComponent(processSizeLabel));
    hGroup.addGroup(layoutProcess.createParallelGroup().
            addComponent(processName).addComponent(processSize));
    layoutProcess.setHorizontalGroup(hGroup);


    GroupLayout.SequentialGroup vGroup = layoutProcess.createSequentialGroup();

    vGroup.addGroup(layoutProcess.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(processNameLabel).addComponent(processName));
    vGroup.addGroup(layoutProcess.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(processSizeLabel).addComponent(processSize));
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
    panelEstadisticas.setPreferredSize(new Dimension(500, 200));
    

  
  
    return(panelEstadisticas);
  }


   static JPanel crearPanelLista() {

    /*  PANEL DE CONFIGURACION DE PROCESOS*/
    JPanel panelProcessList = new JPanel();
    String processTitle = "Lista de Procesos " ;
    
    processTitle = processTitle.substring( processTitle.lastIndexOf('.')+1 );
    panelProcessList.setBorder( new TitledBorder( processTitle ) );
    panelProcessList.setPreferredSize(new Dimension(800, 200));
    

  
    return(panelProcessList);
  }





  
        




  
  
  public MemoryInterface() {
    JPanel panelFila1 = new JPanel();
    String tituloFila1 = "Fila 1 " ;
    tituloFila1 = tituloFila1.substring( tituloFila1.lastIndexOf('.')+1 );
    panelFila1.setBorder( new TitledBorder( tituloFila1 ) );


      panelFila1.add( crearPanelMemoria() );
      panelFila1.add( crearPanelProcesos() );
      panelFila1.add( crearPanelEstadisticas() );
      panelFila1.add( crearPanelLista() );


    


    add(panelFila1);
  
   

 }
  
  public static void main( String args[] ) {
    JFrame frame = new JFrame( "Simulador de memoria" );
    frame.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent evt ) {
        System.exit( 0 );
      }
    });
    frame.getContentPane().add( new MemoryInterface(),BorderLayout.WEST );
    frame.setSize( 1050,600 );
    frame.setVisible( true );
  }
}