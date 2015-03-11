/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2_4;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

/**
 *
 * @author Adrian Portillo
 */
public class Lanzamiento extends JFrame {

    TransformGroup transPrincipal;
    TransformGroup transSecundaria;

    public Lanzamiento() {
        Container GranPanel = getContentPane();
        JPanel Controles = new JPanel(new GridLayout(1, 4));
        Canvas3D dibujo3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        GranPanel.add(dibujo3D, BorderLayout.NORTH);
        GranPanel.add(Controles, BorderLayout.SOUTH);
        dibujo3D.setPreferredSize(new Dimension(780, 580));
        SimpleUniverse simpleU = new SimpleUniverse(dibujo3D);
        BranchGroup escena = crearEscena();
        escena.compile();
//This moves the ViewPlatform back a bit so objects can be viewed.
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(escena);
        Button unBoton = new Button("Despegar");
        unBoton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Despegar(0.001f, 0.002f, 0.003f);
            }
        });
        Controles.add(unBoton);
        pack();
        setVisible(true);
    }

    BranchGroup crearEscena() {
        BranchGroup objRoot = new BranchGroup();
        transPrincipal = new TransformGroup();
        transPrincipal.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transSecundaria = new TransformGroup();
        transSecundaria.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objRoot.addChild(transPrincipal);
        transPrincipal.addChild(transSecundaria);
        transSecundaria.addChild(new ColorCube(0.2));
        return objRoot;
    }

    public void Despegar(float Dx, float Dy, float Dz) //(3)
    {
        Transform3D rotacion = new Transform3D();
        Transform3D desplazamiento = new Transform3D();
        float X = 0f; //posiciones iniciales
        float Y = 0f;
        float Z = 0f; // nota: El observador esta situado a 1.90 hacia el eje Z positivo
        float angulo = 0.0f;
        for (int cont = 0; cont <= 5000; cont++) {
            angulo = angulo + (float) (Math.PI / 100d);
            rotacion.set(new AxisAngle4f(0f, 1f, 0f, angulo)); //rotacion sobre mí, en el punto 0,0,0
            X = X + Dx;  //Se puede controlar la velocidad
            Y = Y + Dy;
            Z = Z + Dz;
            desplazamiento.set(new Vector3f(X, Y, Z));
            transPrincipal.setTransform(desplazamiento);
            transSecundaria.setTransform(rotacion);
            try {
                Thread.sleep(30);
            } catch (Exception e) {;
            }
        }
    }
    public static void main(String args[]) {
        Lanzamiento x = new Lanzamiento();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("Lanzamiento");
        x.setSize(800, 600);
        x.setVisible(true);
    }

    }
