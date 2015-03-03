/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2_2;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Container;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import practicast2.ColorCube2_1;

/**
 *
 * @author Adrian Portillo
 */
public class Elefantes extends JFrame {

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
        //Preparacion de los objetos
        BranchGroup objRoot = new BranchGroup();
        Appearance apariencia = new Appearance();
        Color3f color_material = new Color3f(1f, 1f, 0f);
        Material material = new Material();
        material.setDiffuseColor(color_material);
        material.setSpecularColor(1f, 1f, 0f);
        apariencia.setMaterial(material);
        Box box = new Box(0.3f, 0.2f, 0.1f, null);
        Sphere esfera = new Sphere(0.5f, apariencia);
        //Comienzan las transformaciones
        Transform3D colocar = new Transform3D();
        Transform3D rotacion = new Transform3D();
        colocar.set(new Vector3f(0f, 0.5f, 0.0f));
        TransformGroup colocar_esfera = new TransformGroup(colocar);
        colocar_esfera.addChild(esfera);
        BoundingSphere limites = new BoundingSphere(new Point3d(-5, 0, 5), 100.0); //Localizacion de fuente/paso de luz
        AmbientLight LuzAmbiente = new AmbientLight(new Color3f(0.2f, 0.5f, 0.8f));
        LuzAmbiente.setInfluencingBounds(limites);
        LuzAmbiente.setInfluencingBounds(limites);
        objRoot.addChild(LuzAmbiente);
        objRoot.addChild(box);
        
        Transform3D deformar = new Transform3D();
        
        deformar.setScale(new Vector3d(+3,+1,0));
        TransformGroup reducir_esfera = new TransformGroup(deformar);
        reducir_esfera.addChild(colocar_esfera);
        objRoot.addChild(reducir_esfera);
        //objRoot.addChild(esfera);
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
