/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2_4;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Container;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import practicast2_3.EscenaB;

/**
 *
 * @author Adrian Portillo
 */
public class Reloj extends JFrame {

    Color3f white = new Color3f(1f, 1f, 1f);
    float alturaCilindros = 0.5f;
    BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100.0);
    SimpleUniverse universo;
    float anguloAntebrazo = 45f;
    TransformGroup preparaAntebrazoParaRotableTG;

    public Reloj() {
        Container miPanel = getContentPane();
        Canvas3D zonaDibujo = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        miPanel.add(zonaDibujo);
        universo = new SimpleUniverse(zonaDibujo);
        BranchGroup escena = crearEscena(anguloAntebrazo);
        escena.compile();
        universo.getViewingPlatform().setNominalViewingTransform(); //Desde 0,0,0 mueve la ViewPlaform un poco para adelante para ver los objetos
        universo.addBranchGraph(escena);
    }

    BranchGroup crearEscena(float anguloAntebrazo) {
        BranchGroup objRoot = new BranchGroup();
        Cylinder cilindro1 = new Cylinder(0.05f, alturaCilindros, new Appearance());
        Cylinder cilindro2 = new Cylinder(0.05f, alturaCilindros, new Appearance());
        Transform3D preparaAntebrazoParaRotable = new Transform3D();
        Transform3D rotaAntebrazo = new Transform3D();
        Transform3D posicionaAntebrazo = new Transform3D();

        posicionaAntebrazo.set(new Vector3f(0f, alturaCilindros / 2f, 0f));
        rotaAntebrazo.rotZ(-anguloAntebrazo);
        preparaAntebrazoParaRotable.set(new Vector3f(0f, alturaCilindros / 2f, 0f));
        TransformGroup posicionaAntebrazoTG = new TransformGroup(posicionaAntebrazo);
        TransformGroup rotaAntebrazoTG = new TransformGroup(rotaAntebrazo);
        //TransformGroup preparaAntebrazoParaRotableTG = new TransformGroup(preparaAntebrazoParaRotable);
        preparaAntebrazoParaRotableTG = new TransformGroup(preparaAntebrazoParaRotable);
        objRoot.addChild(cilindro1);
        objRoot.addChild(posicionaAntebrazoTG);
        posicionaAntebrazoTG.addChild(rotaAntebrazoTG);
        rotaAntebrazoTG.addChild(preparaAntebrazoParaRotableTG);
        
        preparaAntebrazoParaRotableTG.addChild(cilindro2);
        preparaAntebrazoParaRotableTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        return objRoot;
    }
    
    public void rotar() {
        Transform3D rotacion = new Transform3D();
        float this_angulo = 0.0f;
        for (int i = 0; i < 361; i++) {
            anguloAntebrazo=(float) i;
            
        }
    }
        public static void main(String args[]) {
        EscenaB x = new EscenaB();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("Reloj");
        x.setSize(800, 600);
        x.setVisible(true);
    }
}