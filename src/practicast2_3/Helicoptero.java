/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2_3;

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

/**
 *
 * @author Adrian Portillo
 */
public class Helicoptero extends JFrame {

    Color3f white = new Color3f(1f, 1f, 1f);
    float alturaCilindros = 0.5f;
    BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100.0);
    SimpleUniverse universo;
    float alpha = 45f;
    float beta = 45f;

    public Helicoptero() {
        Container miPanel = getContentPane();
        Canvas3D zonaDibujo = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        miPanel.add(zonaDibujo);
        universo = new SimpleUniverse(zonaDibujo);
        BranchGroup escena = crearEscena(alpha,beta);
        escena.compile();
        universo.getViewingPlatform().setNominalViewingTransform(); //Desde 0,0,0 mueve la ViewPlaform un poco para adelante para ver los objetos
        universo.addBranchGraph(escena);
    }

    BranchGroup crearEscena(float alpha, float beta) {
        BranchGroup objRoot = new BranchGroup();
        Cylinder cilindro1 = new Cylinder(0.05f, alturaCilindros, new Appearance());
        Cylinder cilindro2 = new Cylinder(0.05f, alturaCilindros, new Appearance());
        Transform3D preparaAntebrazoParaRotable = new Transform3D();
        Transform3D rotaAntebrazo = new Transform3D();
        Transform3D posicionaAntebrazo = new Transform3D();
        Transform3D rotaBrazo = new Transform3D();
        
        
        rotaBrazo.rotZ(-beta * (Math.PI / 180));
        posicionaAntebrazo.set(new Vector3f(0f, alturaCilindros / 2f, 0f));
        rotaAntebrazo.rotZ(-alpha * (Math.PI / 180));
        preparaAntebrazoParaRotable.set(new Vector3f(0f, alturaCilindros / 2f, 0f));
        TransformGroup posicionaAntebrazoTG = new TransformGroup(posicionaAntebrazo);
        TransformGroup rotaAntebrazoTG = new TransformGroup(rotaAntebrazo);
        TransformGroup preparaAntebrazoParaRotableTG = new TransformGroup(preparaAntebrazoParaRotable);
        TransformGroup rotaBrazoTG = new TransformGroup(rotaBrazo);
        TransformGroup posicionaBrazoTG = new TransformGroup(preparaAntebrazoParaRotable);
        
        
        BranchGroup bg_dual = new BranchGroup();
        posicionaAntebrazoTG.addChild(cilindro2);
        rotaAntebrazoTG.addChild(posicionaAntebrazoTG);
        preparaAntebrazoParaRotableTG.addChild(rotaAntebrazoTG);
        bg_dual.addChild(preparaAntebrazoParaRotableTG);
        bg_dual.addChild(cilindro1);
        posicionaBrazoTG.addChild(bg_dual);
        rotaBrazoTG.addChild(posicionaBrazoTG);
        objRoot.addChild(rotaBrazoTG);
        return objRoot;
    }
        public static void main(String args[]) {
        Helicoptero x = new Helicoptero();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("Escena C");
        x.setSize(800, 600);
        x.setVisible(true);
    }
}
