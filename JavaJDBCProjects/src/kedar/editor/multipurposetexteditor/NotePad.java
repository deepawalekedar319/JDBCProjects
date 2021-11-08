package kedar.editor.multipurposetexteditor;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.print.*;
import java.io.*;			//add numbers by using jsplitpane greek
import javax.swing.filechooser.*;
@SuppressWarnings("serial")
public class NotePad extends JFrame 
{							
	JTextArea text = new JTextArea(40,80);
    JMenuBar menuBar;
	JMenu file,edit,view,subfont,textsize,textcolor,backcolor,openall,fontstyle,saveAs,lang,clang,css,perl,ajax,php,xml,cpp,java,python,html,editors,help;
	public static Font font;
	public static String presentfont;
	public static File openfile;
	public static PrintWriter pw ;
	int r;
	public static JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
	public NotePad() 
	{
		font=new Font("Arial",Font.BOLD,20);
		text.setFont(font);
        menuBar = new JMenuBar();
		Image icon = Toolkit.getDefaultToolkit().getImage("D:/NotePadProject/logo2.jpg");
		this.setIconImage(icon);
		ImageIcon fileicon = new ImageIcon("D:/NotePadProject/unnamed.jpeg");
		ImageIcon openicon = new ImageIcon("D:/NotePadProject/open.jpeg");
		@SuppressWarnings("unused")
		ImageIcon saveallicon = new ImageIcon("D:/NotePadProject/saveallicon.png");
		ImageIcon saveicon = new ImageIcon("D:/NotePadProject/save1.png");
		ImageIcon printicon = new ImageIcon("D:/NotePadProject/printicon.png");
		ImageIcon copyicon = new ImageIcon("D:/NotePadProject/copy.png");
		ImageIcon pasteicon = new ImageIcon("D:/NotePadProject/paste.png");
		@SuppressWarnings("unused")
		ImageIcon coloricon = new ImageIcon("D:/NotePadProject/color.png");
		ImageIcon cuticon = new ImageIcon("D:/NotePadProject/cut.png");
		ImageIcon exiticon = new ImageIcon("D:/NotePadProject/exit.png");
		@SuppressWarnings("unused")
		ImageIcon fonticon = new ImageIcon("D:/NotePadProject/font.png");
		ImageIcon selectallicon = new ImageIcon("D:/NotePadProject/selectall.png");
		file = new JMenu("File"); file.setMnemonic('F');
		edit = new JMenu("Edit"); edit.setMnemonic('E');				
		view = new JMenu("View"); view.setMnemonic('V');
		lang = new JMenu("Languages");lang.setMnemonic('L');
		clang = new JMenu("C");lang.setMnemonic('C');
		cpp = new JMenu("CPP");lang.setMnemonic('C');
		java = new JMenu("Java");lang.setMnemonic('J');
		python = new JMenu("Python");lang.setMnemonic('P');
		html = new JMenu("HTML");lang.setMnemonic('H');
		css = new JMenu("CSS");lang.setMnemonic('C');
		perl = new JMenu("Perl");lang.setMnemonic('P');
		ajax = new JMenu("AJAX");lang.setMnemonic('A');
		php = new JMenu("PHP");lang.setMnemonic('P');
		xml = new JMenu("XML");lang.setMnemonic('X');
		editors = new JMenu("Editors");editors.setMnemonic('E');
		openall = new JMenu("Open");openall.setMnemonic('O');
		subfont = new JMenu("Fonts");
		textcolor = new JMenu("Set Text Color");		
		backcolor = new JMenu("Set Background Color");		
		textsize = new JMenu("Text Size");
		fontstyle = new JMenu("Font Style");
		saveAs = new JMenu("SaveAs");
		help=new JMenu("Help");help.setMnemonic('H');
		setTitle("MultipurposeTextEditor-Untitled.txt");
		subfont.setFont(new Font("Arial",Font.BOLD,15));
		textcolor.setFont(new Font("Arial",Font.BOLD,15));
		backcolor.setFont(new Font("Arial",Font.BOLD,15));
		textsize.setFont(new Font("Arial",Font.BOLD,15));
		fontstyle.setFont(new Font("Arial",Font.BOLD,15));
		text.setSelectedTextColor(Color.BLUE);
		//adding functionality
		JMenuItem cw=new JMenuItem("C - [write]");
		JMenuItem co=new JMenuItem("C - [open]      F1");
		JMenuItem cs=new JMenuItem("C - [save]       F2");
		JMenuItem cppw=new JMenuItem("Cpp - [write]");
		JMenuItem cppo=new JMenuItem("Cpp - [open]      F3");
		JMenuItem cpps=new JMenuItem("Cpp - [save]       F4");
		JMenuItem javaw=new JMenuItem("Java - [write]");
		JMenuItem javao=new JMenuItem("Java - [open]      F5");
		JMenuItem javas=new JMenuItem("Jave - [save]       F6");
		JMenuItem pythonw=new JMenuItem("Python - [write]");
		JMenuItem pythono=new JMenuItem("Python - [open]      F9");
		JMenuItem pythons=new JMenuItem("Python - [save]       F10");
		JMenuItem htmlw=new JMenuItem("HTML - [write]");
		JMenuItem htmlo=new JMenuItem("HTML - [open]         F7");
		JMenuItem htmls=new JMenuItem("HTML - [save]         F8");
		JMenuItem cssw=new JMenuItem("CSS - [write]");
		JMenuItem csso=new JMenuItem("CSS - [open]");
		JMenuItem csss=new JMenuItem("CSS - [save]");
		JMenuItem perlw=new JMenuItem("Perl - [write]");
		JMenuItem perlo=new JMenuItem("Perl - [open]");
		JMenuItem perls=new JMenuItem("Perl - [save]");
		JMenuItem ajaxw=new JMenuItem("AJAX - [write]");
		JMenuItem ajaxo=new JMenuItem("AJAX - [open]");
		JMenuItem ajaxs=new JMenuItem("AJAX - [save]");
		JMenuItem phpw=new JMenuItem("PHP - [write]");
		JMenuItem phpo=new JMenuItem("PHP - [open]");
		JMenuItem phps=new JMenuItem("PHP - [save]");
		JMenuItem xmlw=new JMenuItem("XML - [write]");
		JMenuItem xmlo=new JMenuItem("XML - [open]");
		JMenuItem xmls=new JMenuItem("XML - [save]");
		JMenuItem n=new JMenuItem("new  ctrl+n",fileicon);
		JMenuItem open=new JMenuItem("open  ctrl+o",openicon);
		JMenuItem save=new JMenuItem("save  ctrl+s",saveicon);
		JMenuItem pdf=new JMenuItem("PDF ");
		JMenuItem asc=new JMenuItem("C ");
		JMenuItem ascpp=new JMenuItem("CPP ");
		JMenuItem asjava=new JMenuItem("JAVA ");
		JMenuItem aspython=new JMenuItem("Python ");
		JMenuItem ashtml=new JMenuItem("HTML ");
		JMenuItem ascss=new JMenuItem("CSS ");
		//JMenuItem asjs=new JMenuItem("JS ");
		JMenuItem openallfiles=new JMenuItem("Open MultipurposeTextEditor Directory");
		pdf.setFont(new Font("Arial",Font.BOLD,15));
		JMenuItem ftxt=new JMenuItem("File.txt");
		ftxt.setFont(new Font("Arial",Font.BOLD,15));
		JMenuItem print=new JMenuItem("print  ctrl+p ",printicon);
		JMenuItem cut=new JMenuItem("cut  ctrl+x",cuticon);    
		JMenuItem copy=new JMenuItem("copy  ctrl+c",copyicon);    
		JMenuItem paste=new JMenuItem("paste  ctrl+v",pasteicon);    
		JMenuItem selectAll=new JMenuItem("selectAll  ctrl+a",selectallicon);  
		JMenuItem exit = new JMenuItem("exit",exiticon);
		JMenuItem arial = new JMenuItem("Arial");
		JMenuItem paint = new JMenuItem("Paint");
		//JMenuItem wordpad = new JMenuItem("WordPad");
		JMenuItem cmd = new JMenuItem("Cmd  F12");
		arial.setFont(new Font("Arial",Font.BOLD,20));
		JMenuItem comic = new JMenuItem("Comic Sans MS");
		comic.setFont(new Font("Comic Sans MS",Font.BOLD,20));
		JMenuItem cambria = new JMenuItem("Cambria");
		cambria.setFont(new Font("Cambria",Font.BOLD,20));
		JMenuItem consolas = new JMenuItem("Consolas");
		consolas.setFont(new Font("Consolas",Font.BOLD,20));
		JMenuItem fixedsys = new JMenuItem("Fixedsys");
		fixedsys.setFont(new Font("Fixedsys",Font.BOLD,20));
		JMenuItem times = new JMenuItem("Times New Roman");
		times.setFont(new Font("Times New Roman",Font.BOLD,20));
		JMenuItem franklin= new JMenuItem("Franklin Gothic Medium");
		franklin.setFont(new Font("Franklin Gothic Medium",Font.BOLD,20));
		JMenuItem serif = new JMenuItem("MS Serif");
		serif.setFont(new Font("MS Serif",Font.BOLD,20));
		JMenuItem bold = new JMenuItem("Bold");
		bold.setFont(new Font("Arial",Font.BOLD,20));		
		JMenuItem italic = new JMenuItem("Italic");
		italic.setFont(new Font("Arial",Font.ITALIC,20));
		JMenuItem center = new JMenuItem("CENTER BASELINE");
		center.setFont(new Font("Arial",Font.CENTER_BASELINE,20));
		JMenuItem plain = new JMenuItem("Plain");
		plain.setFont(new Font("Arial",Font.PLAIN,20));
		JMenuItem a = new JMenuItem("10");
		JMenuItem b = new JMenuItem("15");				
		JMenuItem c = new JMenuItem("20");
		JMenuItem d = new JMenuItem("25");
		JMenuItem e = new JMenuItem("30");
		JMenuItem f = new JMenuItem("35");
		JMenuItem g = new JMenuItem("40");
		JMenuItem h = new JMenuItem("45");
		JMenuItem i = new JMenuItem("50");
		JMenuItem  blue = new JMenuItem("BLUE ");
		blue.setBackground(Color.BLUE);
		JMenuItem  red = new JMenuItem("RED");
		red.setBackground(Color.RED);
		JMenuItem  black = new JMenuItem("BLACK ");
		black.setBackground(Color.BLACK);
		JMenuItem  white = new JMenuItem("WHITE ");
		white.setBackground(Color.WHITE);
		JMenuItem  cyan = new JMenuItem("CYAN");
		cyan.setBackground(Color.CYAN);
		JMenuItem  gray = new JMenuItem("GRAY ");
		gray.setBackground(Color.GRAY);
		JMenuItem  magenta = new JMenuItem("MAGENTA ");
		magenta.setBackground(Color.MAGENTA);
		JMenuItem  orange = new JMenuItem("ORANGE ");
		orange.setBackground(Color.ORANGE);
		JMenuItem  pink  = new JMenuItem("PINK ");
		pink.setBackground(Color.PINK);	
		JMenuItem  backgray  = new JMenuItem("LIGHT GRAY");
		backgray.setBackground(Color.lightGray);
		JMenuItem  backpink  = new JMenuItem("PINK ");
		backpink.setBackground(Color.pink);
		JMenuItem  backblack  = new JMenuItem("BLACK ");
		backblack.setBackground(Color.BLACK);
		JMenuItem  backwhite  = new JMenuItem("WHITE ");
		backwhite.setBackground(Color.white);
		//JMenuItem  pink  = new JMenuItem("PINK ");
		JMenuItem abt = new JMenuItem("About Editor...");
		abt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				try
				{
					Runtime rTime = Runtime.getRuntime();
					String url = "D:/NotePadProject/help.html";
					String browser = "C:/Program Files/Google/Chrome/Application/chrome ";
					Process pc = rTime.exec(browser + url);
					pc.waitFor();
				}
				catch(Exception help){}
			}
		});
		openallfiles.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==openallfiles) openFile("MultipurposeTextEditor","TextFiles");
			}
		});
		//Languages 
		// c language
		cw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==cw)	text.setText("/* Welcome to C-language \n Make sure that while saving provide suitable extentions*/\n\n#include<stdio.h>\n#include<conio.h>\nint main()\n{\nprintf(\"Hello World !!!\");\n}");
			}
		});
		co.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==co) openFile("C - Open","C - Files");				
			}
		});
		cs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==cs) saveFile("C - Save","C - Files");				
			}
		});
		
		//cpp language
		cppw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==cppw)	text.setText("/* Welcome to Cpp-language \n Make sure that while saving provide suitable extentions*/\n\n#include<iostream>\nint main()\n{\ncout<<\"Hello World !!!\";\n}");
			}
		});
		cppo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==cppo) openFile("CPP - Open","CPP - Files");				
			}
		});
		cpps.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==cpps) saveFile("CPP - Save","CPP - Files");				
			}
		});

		//java language
		javaw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==javaw)	text.setText("/* Welcome to Java-language \n Make sure that while saving provide suitable extentions*/\n\nclass\n{\npublic static void main(String[] args)\n{\nSystem.out.println(\"Hello World !!! \");\n}\n}\n");
			}
		});
		javao.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==javao) openFile("Java - Open","Java - Files");				
			}
		});
		javas.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==javas) saveFile("Java - Save","Java - Files");				
			}
		});

		//html language
		htmlw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==htmlw)	text.setText("<!-- Welcome to HTML-language \n Make sure that while saving provide suitable extentions-->\n\n<!DOCTYPE html>\n<html lang=\"en\">\n <head>\n  <title>Document</title>\n </head>\n <body>\n\n </body>\n</html>");
			}
		});
		htmlo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==htmlo) openFile("HTML - Open","HTML - Files");				
			}
		});
		htmls.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==htmls) saveFile("HTML - Save","HTML - Files");				
			}
		});

		//python language
		pythonw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==pythonw)	text.setText("#Welcome to Python-language \n #Make sure that while saving provide suitable extentions\n\nprint(\"Hello World !!! \")");
			}
		});
		pythono.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==pythono) openFile("Python - Open","Python - Files");				
			}
		});
		pythons.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==pythons) saveFile("Python - Save","Python - Files");				
			}
		});

		//css language
		cssw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==cssw) text.setText("/* Welcome to CSS - Scripting language \n Make sure that while saving provide suitable extentions*/\n\n");
			}
		});
		csso.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==csso) openFile("CSS - Open","CSS - Files");				
			}
		});
		csss.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==csss) saveFile("CSS - Save","CSS - Files");				
			}
		});

		//perl language
		perlw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==perlw) text.setText("# Welcome to Perl - Scripting language \n # Make sure that while saving provide suitable extentions*/\n\n\nuse strict; \nuse warnings; \n\nprint \"Hello, World...\";");
			}
		});
		perlo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==perlo) openFile("Perl- Open","Perl - Files");				
			}
		});
		perls.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==perls) saveFile("Perl - Save","Perl - Files");				
			}
		});

		//Ajax language
		ajaxw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==ajaxw) text.setText("<!--  Welcome to AJAX - Scripting language -->\n <!-- Make sure that while saving provide suitable extentions -->\n\n");
			}
		});
		ajaxo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==ajaxo) openFile("AJAX - Open","AJAX - Files");				
			}
		});
		ajaxs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==ajaxs) saveFile("AJAX - Save","AJAX - Files");				
			}
		});

		//php language
		phpw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==phpw) text.setText("<!--  Welcome to PHP- Scripting language -->\n <!-- Make sure that while saving provide suitable extentions -->\n\n");
			}
		});
		phpo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==phpo) openFile("PHP - Open","PHP - Files");				
			}
		});
		phps.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==phps) saveFile("PHP - Save","PHP - Files");				
			}
		});

		//XML language
		xmlw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==xmlw) text.setText("<!--  Welcome to XML- Scripting language -->\n <!-- Make sure that while saving provide suitable extentions -->\n\n");
			}
		});
		xmlo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==xmlo) openFile("XML - Open","XML - Files");				
			}
		});
		xmls.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==xmls) saveFile("XML- Save","XML - Files");				
			}
		});
		
		//adding button for numbers
		// Editing Selected Text
		text.addMouseListener(new MouseListener()
		{
			public void mouseExited(MouseEvent e){}
			public void mouseEntered(MouseEvent e){}
			public void mousePressed(MouseEvent e){}
			public void mouseClicked(MouseEvent e)
			{
				if (SwingUtilities.isRightMouseButton(e)) popup(e.getX(),e.getY());
				
				/*try
				{
					if (SwingUtilities.isRightMouseButton(e)) popup(e.getX(),e.getY());
				}
				catch (IllegalComponentStateException E)
				{
					System.out.println("Exception at line 288");
				}*/
			}
			public void mouseReleased(MouseEvent e){}
		});
		//adding Paint
		paint.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae) 
			{
				try
				{
					if(ae.getSource()==paint)
					{
						Runtime jvm = Runtime.getRuntime();
						jvm.exec("mspaint");
					}
				}
				catch (IOException e){}
				
			}
		});
		cmd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae) 
			{
				if(ae.getSource()==cmd) opencmd();
			}
		});
		// File Items
		n.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==n ) open();	
			}
		});
		text.addKeyListener(new KeyListener()
		{
			public void keyReleased(KeyEvent e){}
			@SuppressWarnings("deprecation")
			public void keyPressed(KeyEvent e)
			{
				if((e.getKeyCode()==KeyEvent.VK_N) && ((e.getModifiers() & KeyEvent.CTRL_MASK)!=0))	open();
				else if ((e.getKeyCode()==KeyEvent.VK_O) && ((e.getModifiers() & KeyEvent.CTRL_MASK)!=0))		openFile("Open -\"MultipurposeTextEditor\"","TextFiles");
				else if ((e.getKeyCode()==KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK)!=0))		saveFile("Save-\"MultipurposeTextEditor\"","TextFiles");
				else if ((e.getKeyCode()==KeyEvent.VK_P) && ((e.getModifiers() & KeyEvent.CTRL_MASK)!=0))		getPrinter();
				else if((e.getKeyCode()==KeyEvent.VK_F12))opencmd();
				else if((e.getKeyCode()==KeyEvent.VK_F1))openFile("C - Open","C - Files");
				else if((e.getKeyCode()==KeyEvent.VK_F2))saveFile("C - Save","C - Files");
				else if((e.getKeyCode()==KeyEvent.VK_F3))openFile("CPP - Open","CPP - Files");
				else if((e.getKeyCode()==KeyEvent.VK_F4))saveFile("CPP - Save","CPP - Files");
				else if((e.getKeyCode()==KeyEvent.VK_F5))openFile("Java - Open","Java - Files");
				else if((e.getKeyCode()==KeyEvent.VK_F6))saveFile("Java - Save","Java - Files");
				else if((e.getKeyCode()==KeyEvent.VK_F7))openFile("HTML - Open","HTML - Files");
				else if((e.getKeyCode()==KeyEvent.VK_F8))saveFile("HTML - Save","HTML - Files");
				else if((e.getKeyCode()==KeyEvent.VK_F9))openFile("Python - Open","Python - Files");
				else if((e.getKeyCode()==KeyEvent.VK_F10))saveFile("Python - Save","Python - Files");
			}
			public void keyTyped(KeyEvent e){}
		});
		open.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==open )	openFile("Open -\"MultipurposeTextEditor\"","TextFiles");
			}
		});
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==save) saveFile("Save-\"MultipurposeTextEditor\"","TextFiles");
			}
		});
		saveAs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==saveAs || ae.getSource()== "ctrl+s") j.showSaveDialog(save);
			}
		});
		print.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==print || ae.getSource()== "ctrl+p")
				{
					getPrinter();
				}
			}
		});
		pdf.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==pdf)
				{
						r = j.showSaveDialog(null); 
					if (r == JFileChooser.APPROVE_OPTION)
					{
						try
						{
							FileOutputStream fos = new FileOutputStream(j.getSelectedFile().getAbsolutePath()+".pdf");
							BufferedWriter br = new BufferedWriter(new FileWriter("Temp.txt"));
							String data = text.getText();
							br.write(data);
							FileInputStream fis = new FileInputStream("Temp.txt");
							int x ;
							while ((x = fis.read())!=-1)	fos.write(x);
							fos.close();
							br.close();
							fis.close();
							new File("Temp.txt").delete();
						}
						catch(Exception e){ System.out.println("Exception " ); }		
					}
					if(j.getSelectedFile() == null);
					else setTitle(j.getSelectedFile().getName());
				}
			}
		});
		ftxt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==ftxt) saveFile("Save-\"MultipurposeTextEditor\"","TextFiles");
			}
		});
		// saving files 

		asc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==asc) saveFile("Save-\"MultipurposeTextEditor\"","C - Files");
			}
		});
		ascpp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==ascpp) saveFile("Save-\"MultipurposeTextEditor\"","CPP - Files");
			}
		});
		asjava.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==asjava) saveFile("Save-\"MultipurposeTextEditor\"","JAVA - Files");
			}
		});
		aspython.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==aspython) saveFile("Save-\"MultipurposeTextEditor\"","PYTHON - Files");
			}
		});
		ashtml.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==ashtml) saveFile("Save-\"MultipurposeTextEditor\"","HTML - Files");
			}
		});
		ascss.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==ascss) saveFile("Save-\"MultipurposeTextEditor\"","CSS - Files");
			}
		});
		/*asjs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource()==asjs) saveFile("Save-\"MultipurposeTextEditor\"","JS - Files");
			}
		});*/

		cut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==cut || ae.getSource()== "ctrl+x") 	text.cut();
			}
		});
		copy.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==copy || ae.getSource()== "ctrl+c")	text.copy();
			}
		});
		paste.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==paste || ae.getSource()== "ctrl+v")	 text.paste();
			}
		});
		selectAll.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==selectAll || ae.getSource()== "ctrl+a") 	text.selectAll();
			}
		}); 
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				System.exit(0);
			}
		}); 
		// Text Size
		a.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==a)
				{
					presentfont = font.getName();
					font =new Font(NotePad.presentfont,font.getStyle(),10);
					text.setFont(font); 
				}
			}
		}); 
		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==b)
				{
					presentfont = font.getName();
					font =new Font(NotePad.presentfont,font.getStyle(),15);
					text.setFont(font); 
				}
			}
		}); 
		c.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==c)
				{
					presentfont = font.getName();
					font =new Font(NotePad.presentfont,font.getStyle(),20);
					text.setFont(font); 
				}
			}
		}); 
		d.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==d)
				{
					presentfont = font.getName();
					font =new Font(NotePad.presentfont,font.getStyle(),25);
					text.setFont(font); 
				}
			}
		}); 
		e.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==e)
				{
					presentfont = font.getName();
					font =new Font(NotePad.presentfont,font.getStyle(),30);
					text.setFont(font);  
				}
			}
		}); 
		f.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==f)
				{
					presentfont = font.getName();
					font =new Font(NotePad.presentfont,font.getStyle(),35);
					text.setFont(font); 
				}
			}
		}); 
		g.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==g)
				{
					presentfont = font.getName();
					font =new Font(NotePad.presentfont,font.getStyle(),40);
					text.setFont(font); 
				}
			}
		}); 
		h.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==h)
				{
					presentfont = font.getName();
					font =new Font(NotePad.presentfont,font.getStyle(),45);
					text.setFont(font); 
				}
			}
		}); 
		i.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==i)
				{
					presentfont = font.getName();
					font =new Font(NotePad.presentfont,font.getStyle(),50);
					text.setFont(font); 
				}
			}
		}); 
		// Fonts 
		arial.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==arial)
				{
					NotePad.font=new Font("Arial",Font.BOLD,font.getSize());
					text.setFont(font);
				}
			}
		}); 
		comic.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==comic)
				{
					NotePad.font=new Font("Comic Sans MS",Font.BOLD,font.getSize());
					text.setFont(font);
				}
			}
		});
		cambria.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==cambria)
				{
					NotePad.font=new Font("Cambria",Font.BOLD,font.getSize());
					text.setFont(font);
				}
			}
		});
		consolas.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==consolas)
				{
					NotePad.font=new Font("Consolas",Font.BOLD,font.getSize());
					text.setFont(font);
				}
			}
		});
		fixedsys.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==fixedsys)
				{
					NotePad.font=new Font("Fixedsys",Font.BOLD,font.getSize());
					text.setFont(font);
				}
			}
		});
		times.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==times)
				{
					NotePad.font=new Font("Times New Roman",Font.BOLD,font.getSize());
					text.setFont(font);
				}
			}
		});
		franklin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)		
			{
				if(ae.getSource()==franklin)
				{
					NotePad.font=new Font("Franklin Gothic Medium",Font.BOLD,font.getSize());
					text.setFont(font);
				}
			}
		});
		serif.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==serif)
				{
					NotePad.font=new Font("MS Serif",Font.BOLD,font.getSize());
					text.setFont(font);
				}
			}
		});
		// text colors 
		blue.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==blue)
				{
					text.setForeground(Color.BLUE);
				}
			}
		});
		red.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==red)
				{
					text.setForeground(Color.RED);
				}
			}
		});
		black.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==black)
				{
					text.setForeground(Color.BLACK);
				}
			}
		});
		white.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==white)
				{
					text.setForeground(Color.WHITE);
				}
			}
		});
		cyan.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==cyan)
				{
					text.setForeground(Color.CYAN);
				}
			}
		});
		magenta.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==magenta)
				{
					text.setForeground(Color.MAGENTA);
				}
			}
		});
		pink.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==pink)
				{
					text.setForeground(Color.PINK);
				}
			}
		});
		orange.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==orange)
				{
					text.setForeground(Color.ORANGE);
				}
			}
		});
		//background colors
		backwhite.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==backwhite)
				{
					text.setBackground(Color.white);
				}
			}
		});
		backblack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==backblack)
				{
					text.setBackground(Color.black);
				}
			}
		});
		backpink.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==backpink)
				{
					text.setBackground(Color.pink);
				}
			}
		});
		backgray.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==backgray)
				{
					text.setBackground(Color.lightGray);
				}
			}
		});
		// Styles
		bold.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==bold)
				{
					NotePad.font=new Font(presentfont,Font.BOLD,font.getSize());
					text.setFont(font);
				}
			}
		});
		italic.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==italic)
				{
					NotePad.font=new Font(presentfont,Font.ITALIC,font.getSize());
					text.setFont(font);
				}
			}
		});
		plain.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==plain)
				{
					NotePad.font=new Font(presentfont,Font.PLAIN,font.getSize());
					text.setFont(font);
				}
			}
		});
		center.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==center)
				{
					NotePad.font=new Font(presentfont,Font.CENTER_BASELINE,font.getSize());
					text.setFont(font);
				}
			}
		});
		file.add(n);
		file.add(open);
		file.add(save);
		file.add(saveAs);
		saveAs.add(pdf);
		saveAs.add(asc);
		saveAs.add(ascpp);
		saveAs.add(asjava);
		saveAs.add(aspython);
		saveAs.add(ashtml);
		saveAs.add(ascss);
		//saveAs.add(asjs);
		saveAs.add(ftxt);
		file.add(exit);
		file.add(print);
		edit.add(cut);
		edit.add(copy);	
		edit.add(paste);
		edit.add(selectAll);
		edit.add(exit);
		view.add(subfont);
		view.add(textsize);
		view.add(fontstyle);
		fontstyle.add(bold);
		fontstyle.add(italic);
		fontstyle.add(plain);
		fontstyle.add(center);
		textsize.add(a);
		textsize.add(b);
		textsize.add(c);
		textsize.add(d);
		textsize.add(e);
		textsize.add(f);
		textsize.add(g);
		textsize.add(h);
		textsize.add(i);
		subfont.add(arial);
		subfont.add(comic);
		subfont.add(cambria);
		subfont.add(consolas);
		subfont.add(fixedsys);
		subfont.add(serif);
		subfont.add(franklin);
		subfont.add(times);
		textcolor.add(orange);
		textcolor.add(black);
		textcolor.add(white);
		textcolor.add(pink);
		textcolor.add(cyan);
		textcolor.add(red);
		textcolor.add(blue);
		textcolor.add(pink);
		textcolor.add(magenta);
		backcolor.add(backgray);
		backcolor.add(backpink);
		backcolor.add(backblack);
		backcolor.add(backwhite);
		lang.add(clang);
		clang.add(cw);
		clang.add(co);
		clang.add(cs);
		lang.add(cpp);
		cpp.add(cppw);
		cpp.add(cppo);
		cpp.add(cpps);
		lang.add(java);
		java.add(javaw);
		java.add(javao);
		java.add(javas);
		lang.add(python);
		python.add(pythonw);
		python.add(pythono);
		python.add(pythons);
		lang.add(html);
		html.add(htmlw);
		html.add(htmlo);
		html.add(htmls);
		lang.add(css);
		css.add(cssw);
		css.add(csso);
		css.add(csss);
		lang.add(perl);
		perl.add(perlw);
		perl.add(perlo);
		perl.add(perls);
		lang.add(ajax);
		ajax.add(ajaxw);
		ajax.add(ajaxo);
		ajax.add(ajaxs);
		lang.add(php);
		php.add(phpw);
		php.add(phpo);
		php.add(phps);
		lang.add(xml);
		xml.add(xmlw);
		xml.add(xmlo);
		xml.add(xmls);
		view.add(textcolor);
		view.add(backcolor);
		editors.add(paint);
		editors.add(cmd);
		help.add(abt);
		openall.add(openallfiles);
        menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(view);
		menuBar.add(lang);
		menuBar.add(editors);
		menuBar.add(help);
		menuBar.add(openall);
        setJMenuBar(menuBar);
        add(new JScrollPane(text));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
    }

    public  void createAndShowGui()
	{
		JFrame frame = new NotePad();
		/*JScrollPane jsp = new JScrollPane();
		textline = new JTextArea("1");
		textline.setFont(new Font("Arial",Font.BOLD,20));
		textline.setBackground(Color.LIGHT_GRAY);
		textline.setEditable(false);
		text.getDocument().addDocumentListener(new DocumentListener(){
			public String getText(){
				int caretPosition = text.getDocument().getLength();
				Element root = text.getDocument().getDefaultRootElement();
				String text = "1" + System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
					text += i + System.getProperty("line.separator");
				}
				return text;
			}
			@Override
			public void changedUpdate(DocumentEvent de) {
				textline.setText(getText());
			}
 
			@Override
			public void insertUpdate(DocumentEvent de) {
				textline.setText(getText());
			}
 
			@Override
			public void removeUpdate(DocumentEvent de) {
				textline.setText(getText());
			}
 
		});				//Problem is when we r using this right click is not working and 
						//we Cant open a file directly [ goto line 288 ]

		jsp.getViewport().add(text);
		jsp.setRowHeaderView(textline);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.add(jsp); */
		frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e)
		{
			int response = JOptionPane.showConfirmDialog(null, "Do you want to exit ?", "Exit",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (response == JOptionPane.NO_OPTION)	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			else if (response == JOptionPane.YES_OPTION)		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			else if (response == JOptionPane.CLOSED_OPTION) frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	});
        frame.pack();
        frame.setVisible(true);
	 }
	public  void openFile(String filename,String langfiles)
	{
		File file = new File("MultipurposeTextEditor/"+langfiles);
		file.mkdir();
		j.setCurrentDirectory(file);
		j.setDialogTitle(filename);
		int result = j.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) 
		{
			File selectedFile = j.getSelectedFile();
			if(selectedFile == null) ;
			this.setTitle(selectedFile.getName());
			try
			{
				 String r1 = "";
				 String r2 ="";
				 BufferedReader br = new BufferedReader(new FileReader(selectedFile));
				 while((r1 = br.readLine())!=null)   
				 {
					r2 = r2 + r1;
					r2 = r2 + "\n";
				 }
				text.setText(r2);					
				br.close();    
			}
			catch (IOException e)	{ 	System.out.println("Exception at 946"); 	}
		}
	}
	public void saveFile(String filename,String langfiles)
	{
		try{
		File file = new File("MultipurposeTextEditor/"+langfiles);
		file.mkdir();
		j.setCurrentDirectory(file);
		j.setDialogTitle(filename);
		r = j.showSaveDialog(this); 
		if (r == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				pw = new PrintWriter(new FileWriter(j.getSelectedFile().getAbsolutePath(),true));
				String data = "";
				data = text.getText();
				pw.println(data);
				pw.close();
			}
			catch (Exception e){  }
		}
		if(j.getSelectedFile() == null) ;
		else this.setTitle(j.getSelectedFile().getName());
		/*File fexist = new File(j.getSelectedFile().toString());
		if(fexist.exists())
		{
			int resp = JOptionPane.showConfirmDialog(null,"Do you want to replace the existing file ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (resp != JOptionPane.YES_OPTION) return;
		}
		else ; */
		}
		catch(Exception none){}
	}
	public void open()
	{
		int response = JOptionPane.showConfirmDialog(null, "Do you want to Save ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) 
		{
			dispose();
			this.createAndShowGui();
		}
		else if (response == JOptionPane.YES_OPTION)		saveFile("Save-\"MultipurposeTextEditor\"","TextFiles");
	}
	public void popup(int x,int y)
	{
		JMenuItem n1 = new JMenuItem("New");
		n1.setFont(new Font("Arial",Font.BOLD,15));
		JMenuItem copy = new JMenuItem("Copy");
		copy.setFont(new Font("Arial",Font.BOLD,15));
		JMenuItem paste = new JMenuItem("Paste");
		paste.setFont(new Font("Arial",Font.BOLD,15));
		JMenuItem save = new JMenuItem("Save");
		save.setFont(new Font("Arial",Font.BOLD,15));
		JMenuItem open = new JMenuItem("Open");
		open.setFont(new Font("Arial",Font.BOLD,15));
		JMenuItem delete = new JMenuItem("Delete");
		delete.setFont(new Font("Arial",Font.BOLD,15));
		JMenuItem selectall = new JMenuItem("SelectAll");
		selectall.setFont(new Font("Arial",Font.BOLD,15));
		JMenuItem lower = new JMenuItem("Lower Case");
		lower.setFont(new Font("Arial",Font.BOLD,15));
		JMenuItem upper = new JMenuItem("Upper Case");
		upper.setFont(new Font("Arial",Font.BOLD,15));
		JPopupMenu pop = new JPopupMenu();
		n1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==n1 ) open();	
			}
		});
		copy.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==copy ) text.copy();	
			}
		});
		paste.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==paste ) text.paste();	
			}
		});
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==save) saveFile("Save-\"MultipurposeTextEditor\"","TextFiles");
			}
		});
		open.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==open)	openFile("Save -\"MultipurposeTextEditor","TextFiles");
			}
		});
		delete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==delete) 	text.cut();
			}
		});
		selectall.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==selectall) 	text.selectAll();
			}
		}); 
		lower.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==lower)
				{
					int start=text.getSelectionStart();
					int end=text.getSelectionEnd();
					String edit = text.getSelectedText();
					if(edit == null);
					else text.replaceRange(edit.toLowerCase(),start,end);						
				}
			}
		});
		upper.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==upper)
				{
					int start=text.getSelectionStart();
					int end=text.getSelectionEnd();
					String edit = text.getSelectedText();
					if(edit == null);
					else text.replaceRange(edit.toUpperCase(),start,end);					
				}
			}
		});
		pop.add(n1);
		pop.add(copy);
		pop.add(paste);
		pop.add(save);
		pop.add(open);
		pop.add(delete);
		pop.add(selectall);
		pop.add(lower);
		pop.add(upper);
		pop.show(this,x,y);
	}
	public void opencmd() 
	{
		try
		{
			Runtime.getRuntime().exec(new String[] {"cmd", "/K", "Start"}); 
		}
		catch (IOException e){}		
	}
	public void getPrinter()
	{
		PrinterJob printer = PrinterJob.getPrinterJob();
		@SuppressWarnings("unused")
		PageFormat pFormat = printer.defaultPage();
		printer.printDialog();
		try
		{
			printer.print(); 
		}
		catch (Exception PrintException) {}
	}
	public static void main(String[] args)
	{
        NotePad s = new NotePad();
		       s.createAndShowGui();
    }
}