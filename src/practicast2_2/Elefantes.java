/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2_2;

import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Container;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.swing.JFrame;

/**
 *
 * @author Adrian Portillo
 */
public class Elefantes extends JFrame{
 SimpleUniverse universo;

    public Elefantes() {
        Container miPanel = getContentPane();
        Canvas3D zonaDibujo = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        miPanel.add(zonaDibujo);
        universo = new SimpleUniverse(zonaDibujo);
        BranchGroup escena = crearEscena();
        escena.compile();
        universo.getViewingPlatform().setNominalViewingTransform(); //Desde 0,0,0 mueve la ViewPlaform un poco para adelante para ver los objetos
        universo.addBranchGraph(escena);
    }   
    BranchGroup crearEscena() {
        BranchGroup objRoot = new BranchGroup();
        return objRoot;
        
    }
        public static void main(String args[]) {
        Elefantes x = new Elefantes();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("El cazador de elefantes");
        x.setSize(800, 600);
        x.setVisible(true);
    }
}
