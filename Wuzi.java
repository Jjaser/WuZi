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

    JButton b1 = new JButton("开始游戏");
    JButton b2 = new JButton("上一步");
    JButton b3 = new JButton("下一步");
    JButton b4 = new JButton("重置游戏");

    public Wuzi()
    {

        super("五子棋");
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

    public void actionPerformed(ActionEvent e) // 识别按钮
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

    public void gameStart()            // b1实现
    {
        isStart = true;
        enableGame(true);
        b1.setEnabled(false);
    }

    public void enableGame(boolean e)  // 设置组件状态
    {
        b2.setEnabled(e);
        b3.setEnabled(e);
        b4.setEnabled(e);

    }

    public void reset() // b4实现
    {
        isStart = false;
        b1.setEnabled(true);
        enableGame(false);
        repaint();
    }

    public void setDown(int x, int y)    // 下棋
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


        if (Win1(x / 50, y / 50))                                                         // 赢了弹窗
        {
            JOptionPane.showMessageDialog(null, startC(color) + "获胜！");
            isStart = false;
        }

        if (Win2(x / 50, y / 50))
        {
            JOptionPane.showMessageDialog(null, startC(color) + "获胜！");
            isStart = false;
        }

        if (Win3(x / 50, y / 50))
        {
            JOptionPane.showMessageDialog(null, startC(color) + "获胜！");
            isStart = false;
        }

        if (Win4(x / 50, y / 50))
        {
            JOptionPane.showMessageDialog(null, startC(color) + "获胜！");
            isStart = false;
        }
    }

    public String startC(int x)                                                    //获取先手棋子
    {
        if (x == 0)
        {
            return "黑子";
        } else
        {
            return "白子";
        }
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseClicked(MouseEvent e)                                                // 获取坐标
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

    public boolean Win1(int x, int y)                                    //横着
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

    public boolean Win2(int x, int y)                        // 竖着
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

    public boolean Win3(int x, int y)                                            //  左斜
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

    public boolean Win4(int x, int y)                                // 右斜
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
