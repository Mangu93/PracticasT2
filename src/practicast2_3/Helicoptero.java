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
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
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
    Color3f red = new Color3f(1f, 0f, 0f);
    Color3f blue = new Color3f(0f,0f,1f);
    float alturaCilindros = 0.5f;
    BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100.0);
    SimpleUniverse universo;
    float alpha = ((float) Math.PI / 4f);
    float beta = ((float) Math.PI / 2f);

    public Helicoptero() {
        Container miPanel = getContentPane();
        Canvas3D zonaDibujo = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        miPanel.add(zonaDibujo);
        universo = new SimpleUniverse(zonaDibujo);
        BranchGroup escena = crearEscena(alpha, beta);
        escena.compile();
        universo.getViewingPlatform().setNominalViewingTransform(); //Desde 0,0,0 mueve la ViewPlaform un poco para adelante para ver los objetos
        universo.addBranchGraph(escena);
    }

    BranchGroup crearEscena(float alpha, float beta) {
        BranchGroup objRoot = new BranchGroup();
        Appearance apariencia_roja = new Appearance();
        Appearance apariencia_azul = new Appearance();
        
        Material mat = new Material();
        mat.setDiffuseColor(red);  //color cuando hay luz direccional
        mat.setSpecularColor(new Color3f(1f, 1f, 0f));  //color amarillo para el reflejo de luz
        mat.setShininess(128f); //brillo máximo de reflejo de luz
        Material mat2 = new Material();
        mat2.setDiffuseColor(red);  //color cuando hay luz direccional
        mat2.setSpecularColor(new Color3f(1f, 1f, 0f));  //color amarillo para el reflejo de luz
        mat2.setShininess(128f); //brillo máximo de reflejo de luz
        apariencia_roja.setMaterial(mat2);
        mat.setDiffuseColor(blue);
        apariencia_azul.setMaterial(mat);
        
        Cylinder cilindro1 = new Cylinder(0.05f, alturaCilindros, apariencia_azul);
        Cylinder cilindro2 = new Cylinder(0.05f, alturaCilindros, apariencia_roja);
        Cylinder cilindro3 = new Cylinder(0.05f, alturaCilindros, apariencia_roja);
        //PRIMERO ROTO EL 2
        Transform3D rota_cil2 = new Transform3D();
        rota_cil2.rotZ(-(Math.PI / 2d));
        TransformGroup rota_cil2_tg = new TransformGroup(rota_cil2);
        rota_cil2_tg.addChild(cilindro2);
        //AHORA LO MUEVO
        Transform3D mueve_cil2 = new Transform3D();
        mueve_cil2.set(new Vector3f(0f, alturaCilindros / 2f, 0f));
        TransformGroup mueve_cil2_tg = new TransformGroup(mueve_cil2);
        mueve_cil2_tg.addChild(rota_cil2_tg);

        //REPLICO EL 2 EN EL 3
        TransformGroup rota_cil3_tg = new TransformGroup(rota_cil2);
        TransformGroup mueve_cil3_tg = new TransformGroup(mueve_cil2);
        rota_cil3_tg.addChild(cilindro3);
        mueve_cil3_tg.addChild(rota_cil3_tg);
        Transform3D rota_y_cil3 = new Transform3D();
        rota_y_cil3.rotY(-alpha);
        TransformGroup rota_y_tg = new TransformGroup(rota_y_cil3);
        rota_y_tg.addChild(mueve_cil3_tg);
        objRoot.addChild(cilindro1);
        objRoot.addChild(mueve_cil2_tg);
        objRoot.addChild(rota_y_tg);
        BranchGroup final_tg = new BranchGroup();

            Vector3f light1Direction = new Vector3f(-1.0f, 1.0f, -1.0f);
        // light coming from left, up, and back quadrant
        Vector3f light2Direction = new Vector3f(1.0f, 1.0f, 1.0f);
        // light coming from right, up, and front quadrant
        DirectionalLight light1
                = new DirectionalLight(white, light1Direction);
        light1.setInfluencingBounds(bounds);
        DirectionalLight light2
                = new DirectionalLight(white, light2Direction);
        light2.setInfluencingBounds(bounds);
        Transform3D giralotodopapi = new Transform3D();
        giralotodopapi.rotX(alpha);
        TransformGroup giralotodoTG = new TransformGroup(giralotodopapi);
        giralotodoTG.addChild(objRoot);
        final_tg.addChild(giralotodoTG);
        final_tg.addChild(light2);
        final_tg.addChild(light1);
        return final_tg;
    }

    public static void main(String args[]) {
        Helicoptero x = new Helicoptero();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("Helicoptero");
        x.setSize(800, 600);
        x.setVisible(true);
    }
}
