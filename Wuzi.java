import com.sun.org.apache.regexp.internal.REUtil;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Wuzi extends JFrame implements ActionListener, MouseListener, MouseMotionListener
{
    int color = 0;

    boolean isStart = false;

    int a[][] = new int[16][16];

    JButton b1 = new JButton("��ʼ��Ϸ");
    JButton b2 = new JButton("��һ��");
    JButton b3 = new JButton("��һ��");
    JButton b4 = new JButton("������Ϸ");

    public Wuzi()
    {

        super("������");
        this.setBounds(50, 50, 800, 850);
        this.setLayout(null);

        addMouseListener(this);

        JPanel p = new JPanel();
        p.setBounds(0, 0, 800, 850);
        p.setLayout(null);
        this.add(p);

        b1.setBounds(35, 740, 120, 50);
        b2.setBounds(230, 740, 120, 50);
        b3.setBounds(427, 740, 120, 50);
        b4.setBounds(625, 740, 120, 50);

        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);

        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) // ʶ��ť
    {
        if (e.getSource() == b1)
        {
            gameStart();
        }
        if (e.getSource() == b4)
        {
            reset();
        }
    }

    public void gameStart()            // b1ʵ��
    {
        isStart = true;
        enableGame(true);
        b1.setEnabled(false);
    }

    public void enableGame(boolean e)  // �������״̬
    {
        b2.setEnabled(e);
        b3.setEnabled(e);
        b4.setEnabled(e);

    }

    public void reset() // b4ʵ��
    {
        isStart = false;
        b1.setEnabled(true);
        enableGame(false);
        repaint();
    }

    public void setDown(int x, int y)    // ����
    {
        if (!isStart)
        {
            return;
        }

        if (a[x / 50][y / 50] != 0)
        {
            return;
        }
        Graphics g = getGraphics();

        if (color == 1)
        {
            g.setColor(Color.black);
            color = 0;
        } else
        {
            g.setColor(Color.white);
            color = 1;
        }

        g.fillOval(x - 25, y - 25, 50, 50);

        a[x / 50][y / 50] = color + 1;


        if (Win1(x / 50, y / 50))                                                         // Ӯ�˵���
        {
            JOptionPane.showMessageDialog(null, startC(color) + "��ʤ��");
            isStart = false;
        }

        if (Win2(x / 50, y / 50))
        {
            JOptionPane.showMessageDialog(null, startC(color) + "��ʤ��");
            isStart = false;
        }

        if (Win3(x / 50, y / 50))
        {
            JOptionPane.showMessageDialog(null, startC(color) + "��ʤ��");
            isStart = false;
        }

        if (Win4(x / 50, y / 50))
        {
            JOptionPane.showMessageDialog(null, startC(color) + "��ʤ��");
            isStart = false;
        }
    }

    public String startC(int x)                                                    //��ȡ��������
    {
        if (x == 0)
        {
            return "����";
        } else
        {
            return "����";
        }
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseClicked(MouseEvent e)                                                // ��ȡ����
    {
        int x1, y1;
        x1 = e.getX();
        y1 = e.getY();
        if (e.getX() < 50 || e.getX() > 750 || e.getY() < 50 || e.getY() > 750)
        {
            return;
        }

        if (x1 % 50 > 25)
        {
            x1 += 50;
        }

        if (y1 % 50 > 25)
        {
            y1 += 50;
        }

        x1 = x1 / 50 * 50;
        y1 = y1 / 50 * 50;
        setDown(x1, y1);

    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseDragged(MouseEvent e)
    {
    }

    public void mouseMoved(MouseEvent e)
    {
    }

    public boolean Win1(int x, int y)                                    //����
    {
        int x1, y1, t = 1;
        x1 = x;
        y1 = y;

        for (int i = 1; i < 5; i++)
        {
            if (!OnBoard(x1 + i, y1))
            {
                break;
            }
            if (a[x1 + i][y1] == a[x][y])
            {
                t += 1;
            } else
            {
                break;
            }

        }

        for (int i = 1; i < 5; i++)
        {
            if (!OnBoard(x1 - i, y1))
            {
                break;
            }
            if (a[x1 - i][y1] == a[x][y])
            {
                t += 1;
            } else
            {
                break;
            }
        }

        if (t > 4)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public boolean Win2(int x, int y)                        // ����
    {
        int x1, y1, t = 1;
        x1 = x;
        y1 = y;

        for (int i = 1; i < 5; i++)
        {
            if (!OnBoard(x1, y1 + i))
            {
                break;
            }
            if (a[x1][y1 + i] == a[x][y])
            {
                t += 1;
            } else
            {
                break;
            }

        }

        for (int i = 1; i < 5; i++)
        {
            if (!OnBoard(x1, y1 - i))
            {
                break;
            }

            if (a[x1][y1 - i] == a[x][y])
            {
                t += 1;
            } else
            {
                break;
            }
        }

        if (t > 4)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public boolean Win3(int x, int y)                                            //  ��б
    {
        int x1, y1, t = 1;
        x1 = x;
        y1 = y;

        for (int i = 1; i < 5; i++)
        {
            if (!OnBoard(x1 + i, y1 - i))
            {
                break;
            }
            if (a[x1 + i][y1 - i] == a[x][y])
            {
                t += 1;
            } else
            {
                break;
            }

        }

        for (int i = 1; i < 5; i++)
        {
            if (!OnBoard(x1 - i, y1 + i))
            {
                break;
            }
            if (a[x1 - i][y1 + i] == a[x][y])
            {
                t += 1;
            } else
            {
                break;
            }
        }

        if (t > 4)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public boolean Win4(int x, int y)                                // ��б
    {
        int x1, y1, t = 1;
        x1 = x;
        y1 = y;

        for (int i = 1; i < 5; i++)
        {
            if (!OnBoard(x1 + i, y1 + i))
            {
                break;
            }
            if (a[x1 + i][y1 + i] == a[x][y])
            {
                t += 1;
            } else
            {
                break;
            }

        }

        for (int i = 1; i < 5; i++)
        {
            if (!OnBoard(x1 - i, y1 - i))
            {
                break;
            }

            if (a[x1 - i][y1 - i] == a[x][y])
            {
                t += 1;
            } else
            {
                break;
            }
        }

        if (t > 4)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public boolean OnBoard(int x, int y)
    {
        if ((x > 0) && (x < 16) && (y > 0) && (y < 16)) return true;
        return false;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.lightGray);
        g.fill3DRect(40, 40, 720, 720, true);
        g.setColor(Color.black);
        for (int i = 1; i < 16; i++)
        {
            g.drawLine(50, 50 * i, 750, 50 * i);
            g.drawLine(50 * i, 50, 50 * i, 750);
        }
        g.fillOval(195, 195, 10, 10);
        g.fillOval(595, 195, 10, 10);
        g.fillOval(195, 595, 10, 10);
        g.fillOval(595, 595, 10, 10);
        g.fillOval(395, 395, 10, 10);
    }

    public static void main(String[] args)
    {
        new Wuzi();


    }


}
