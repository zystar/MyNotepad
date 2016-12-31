package text;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class Text extends JFrame{
   
    public static void main(String []args){ 
    	final JFrame jf = new JFrame("新建文本文档");
    	jf.setSize(500, 600);
    	jf.setLocation(450,100);
    	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口时同时结束程序
    	
    	
    	JPanel jp = new JPanel();
    	
    	final JTextArea jta = new JTextArea();
    	jp.setLayout(new BorderLayout());//设置BorderLayout，让JTextArea自动充满
    	JScrollPane jsp=new JScrollPane(jta);
    	jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);//强制滚动条显示
    	
	    JMenuBar jmb = new JMenuBar();//创建一个菜单栏
	
	    JMenu jm1 = new JMenu("文件");//下拉菜单“文件”
	    JMenuItem jmt1_1 = new JMenuItem("打开");
	    JMenuItem jmt1_2 = new JMenuItem("保存");
	    JMenuItem jmt1_3 = new JMenuItem("退出");
	    
	    jm1.add(jmt1_1);
	    jm1.addSeparator();
	    jm1.add(jmt1_2);
	    jm1.addSeparator();
	    jm1.add(jmt1_3);
	
	    JMenu jm2 = new JMenu("编辑");//下拉菜单“编辑”
	    JMenuItem jmt2_1 = new JMenuItem("复制");
	    JMenuItem jmt2_2 = new JMenuItem("剪切");
	    JMenuItem jmt2_3 = new JMenuItem("粘贴");
	    
	    jm2.add(jmt2_1);
	    jm2.addSeparator();
	    jm2.add(jmt2_2);
	    jm2.addSeparator();
	    jm2.add(jmt2_3);
	    
	    JMenu jm3 = new JMenu("格式");//下拉菜单“格式”
	    JMenu jmt3_1 = new JMenu("格式设置");
	    JMenuItem jmt3_1_1 = new JMenuItem("自动换行");
	    JMenuItem jmt3_1_2 = new JMenuItem("取消自动换行");
	    JMenuItem jmt3_1_3 = new JMenuItem("断行不断字");
	    JMenuItem jmt3_1_4 = new JMenuItem("取消断行不断字");
	    
	    jmt3_1.add(jmt3_1_1);
	    jmt3_1.addSeparator();
	    jmt3_1.add(jmt3_1_2);
	    jmt3_1.addSeparator();
	    jmt3_1.add(jmt3_1_3);
	    jmt3_1.addSeparator();
	    jmt3_1.add(jmt3_1_4);
	    
	    jm3.add(jmt3_1);
	    jmb.add(jm1);
	    jmb.add(jm2);
	    jmb.add(jm3);
	    
	    //添加组件
	    jp.add(jsp);//滚动条添加
	    jf.add(jp);
	    jf.setJMenuBar(jmb);
	    
	    //添加右键菜单
	    final JPopupMenu popupMenu = new  JPopupMenu();
		JMenuItem menuOpen = new JMenuItem("打开");
		JMenuItem menuItemSave = new JMenuItem("保存");
		JMenuItem menuItemCopy = new JMenuItem("复制");
		JMenuItem menuItemCut = new JMenuItem("剪切");
		JMenuItem menuItemPaste = new JMenuItem("粘贴");
		popupMenu.add(menuOpen);
		popupMenu.addSeparator();//添加分割线
		popupMenu.add(menuItemSave);
		popupMenu.addSeparator();//添加分割线
		popupMenu.add(menuItemCopy);
		popupMenu.addSeparator();//添加分割线
		popupMenu.add(menuItemCut);
		popupMenu.addSeparator();//添加分割线
		popupMenu.add(menuItemPaste);
		jta.add(popupMenu);
	    
    	
    	//添加事件
    	//给"打开"菜单项注册事件监听器
    	jmt1_1.addActionListener(new ActionListener() {
    	      @Override
    	      public void actionPerformed(ActionEvent e) {
    	    	  FileDialog fd1 = new FileDialog(jf, "打开",FileDialog.LOAD);//定义一个打开文件的对话框
    	    	  fd1.setVisible(true);//对话框可视
    	    	  String strFile = fd1.getDirectory() + fd1.getFile();//getDirectory()获取fd对话框的目录，getFile()获取fd对话框的选定文件
    	    	  if(strFile!=null){
    	    		  try {
    	    			  FileInputStream fis = new FileInputStream(strFile);

    	    			  //fi.available()返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
    	    			  byte bt[] = new byte[fis.available()];

    	    			  fis.read(bt);
    	    			  jta.setText(new String(bt));//将值赋到文本区中(new String(bt)将字符转换成字符串).
    	    			  fis.close();
    	    		  } catch (FileNotFoundException e1) {
    	    			  // TODO 自动生成的 catch 块
    	    			  e1.printStackTrace();
    	    		  } catch (IOException e1) {
    	    			  // TODO 自动生成的 catch 块
    	    			  e1.printStackTrace();
    	    		  }
    	    	  }
    	      }
    	});
    	//给"保存"菜单项注册事件监听器
    	jmt1_2.addActionListener(new ActionListener() {
    	      @Override
    	      public void actionPerformed(ActionEvent e) {
    	    	  

    	    	  FileDialog fd2 = new FileDialog(jf, "保存", FileDialog.SAVE);//FileDialog.SAVE此常量值指示文件对话框窗口的作用是查找要写入的文件。
    	    	  fd2.setVisible(true);//对话框可视
    	    	  String fname = fd2.getDirectory();//getDirectory()获取fd2对话框的目录,貌似不能放在前一行代码前
    	    	  File file = new File(fd2.getFile() + ".txt");//fd2.getFile()获得fd2对话框中的选定文件
    	    	  String s = jta.getText();
    	    	  try {
    	    		  BufferedWriter bw = new BufferedWriter(new FileWriter(fname+file));
					bw.write(s);
					bw.close();
				} catch (Exception ioe) {
					ioe.printStackTrace();
				}

    	      }
    	});
    	//给”退出“菜单项注册事件监听器
    	jmt1_3.addActionListener(new ActionListener() {
    	      @Override
    	      public void actionPerformed(ActionEvent e) {
    	            System.exit(0);
    	      }
    	});
    	//复制
    	jmt2_1.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			jta.copy();
    		}
    	});
    	//剪切
    	jmt2_2.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			jta.cut();
    		}
    	});
    	//粘贴
    	jmt2_3.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			jta.paste();
    		}
    	});
    	//自动换行
    	jmt3_1_1.addActionListener(new ActionListener() {
    	      @Override
    	      public void actionPerformed(ActionEvent e) {
    	    	  jta.setLineWrap(true);   	//自动换行
    	      }
    	});
    	//取消自动换行
    	jmt3_1_2.addActionListener(new ActionListener() {
    	      @Override
    	      public void actionPerformed(ActionEvent e) {
    	    	  jta.setLineWrap(false);
    	      }
    	});
    	//断行不断字
    	jmt3_1_3.addActionListener(new ActionListener() {
    	      @Override
    	      public void actionPerformed(ActionEvent e) {
    	            jta.setWrapStyleWord(true);;
    	      }
    	});
    	//取消断行不断字
    	jmt3_1_4.addActionListener(new ActionListener() {
    	      @Override
    	      public void actionPerformed(ActionEvent e) {
    	            jta.setWrapStyleWord(false);
    	      }
    	});
  	
    	//给JPanel实例对象panel容器注册事件监听器
    	jta.addMouseListener(new MouseListener() {
    	      @Override
    	      public void mousePressed(MouseEvent e) {
    	            int mods=e.getModifiers(); 
    	            if((mods&InputEvent.BUTTON3_MASK)!=0){
    	                  //调用show方法显示弹出式菜单
    	                  popupMenu.show(jta,e.getX(),e.getY());
    	            }
    	      }

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
    	});
    	//给“打开”菜单项注册事件监听器
    	menuOpen.addActionListener(new ActionListener() {
    	      @Override
    	      public void actionPerformed(ActionEvent e) {
    	    	  FileDialog fd1 = new FileDialog(jf, "打开",FileDialog.LOAD);//定义一个打开文件的对话框
    	    	  fd1.setVisible(true);//对话框可视
    	    	  String strFile = fd1.getDirectory() + fd1.getFile();//getDirectory()获取fd对话框的目录，getFile()获取fd对话框的选定文件
    	    	  if(strFile!=null){
    	    		  try {
    	    			  FileInputStream fis = new FileInputStream(strFile);

    	    			  //fi.available()返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
    	    			  byte bt[] = new byte[fis.available()];

    	    			  fis.read(bt);
    	    			  jta.setText(new String(bt));//将值赋到文本区中(new String(bt)将字符转换成字符串).
    	    			  fis.close();
    	    		  } catch (FileNotFoundException e1) {
    	    			  // TODO 自动生成的 catch 块
    	    			  e1.printStackTrace();
    	    		  } catch (IOException e1) {
    	    			  // TODO 自动生成的 catch 块
    	    			  e1.printStackTrace();
    	    		  }
    	    	  }
    	      }
    	});
    	//给”保存“菜单项注册事件监听器
    	menuItemSave.addActionListener(new ActionListener() {
    	      @Override
    	      public void actionPerformed(ActionEvent e) {
    	            System.exit(0);
    	      }
    	});
    	//给”复制“菜单项注册事件监听器
    	menuItemCopy.addActionListener(new ActionListener() {
    	      @Override
    	      public void actionPerformed(ActionEvent e) {
    	    	  jta.copy();
    	      }
    	});
    	//给”剪切“菜单项注册事件监听器
    	menuItemCut.addActionListener(new ActionListener() {
    	      @Override
    	      public void actionPerformed(ActionEvent e) {
    	    	  jta.cut();
    	      }
    	});
    	//给”粘贴“菜单项注册事件监听器
    	menuItemPaste.addActionListener(new ActionListener() {
    	      @Override
    	      public void actionPerformed(ActionEvent e) {
    	    	  jta.paste();
    	      }
    	});


    	jf.setVisible(true);//可视
    }
}

