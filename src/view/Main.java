/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.IOFile;
import controller.Search;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.BanDoc;
import model.BangQuanLy;
import model.Sach;


public class Main extends javax.swing.JFrame {

    private ArrayList<Sach> sach = new ArrayList<>();
    private ArrayList<BanDoc> bandoc = new ArrayList<>();
    private ArrayList<BangQuanLy> qlmuon = new ArrayList<>();
    private String[] search = {"Tat ca","Ma ban doc","Ten ban doc","Ma sach","Ten sach"};
    private DefaultComboBoxModel searchmodel;
    private Vector<BangQuanLy> tam = new Vector<>();
    private Vector<Sach> tambo = new Vector<>();
    private State state;
    public Main() {
        initComponents();
        changeState(State.normal);
        IOFile.inFile(sach,"sach.dat");
        IOFile.inFile(bandoc,"bandoc.dat");
        IOFile.inFile(qlmuon,"qlmuon.dat");        
        combomabd.removeAllItems();
        combomas.removeAllItems();
        for(Sach i : sach){
            combomas.addItem(Integer.toString(i.getMa()));
        }
        for(BanDoc i : bandoc){
            combomabd.addItem(Integer.toString(i.getMa()));
        }
        taoSach();
        taoBandoc();
        taoQLmuon();
        searchmodel = new DefaultComboBoxModel(search);
        combo1.setModel(searchmodel);
        buttonGroup1.add(rd1);
        buttonGroup1.add(rd2);
        taoSach1();
        taoSachNam();
        r1.setActionCommand("r1");
        r2.setActionCommand("r2");
        r3.setActionCommand("r3");
        r4.setActionCommand("r4");
        r5.setActionCommand("r5");
        r6.setActionCommand("r6");
        r7.setActionCommand("r7");
    }    
    private void changeState(State state){
        this.state = state;
        if(state == State.normal){
            setEnableBandoc(false);
            setEnableSach(false);
            setEnableQL(false);
            jTable1.setEnabled(true);
            jTable2.setEnabled(true);
            jTable3.setEnabled(true);
            jTable4.setEnabled(true);
        }
        else if(state == State.themBandoc){
            setEnableBandoc(true);            
            jTable1.setEnabled(false);
        }
        else if(state == State.suaBandoc){
            setEnableBandoc(true);            
            jTable1.setEnabled(false);
        }
        else if(state == State.themSach){
           setEnableSach(true);           
           jTable2.setEnabled(false);
        }
        else if(state == State.suaSach){
            setEnableSach(true);            
            jTable2.setEnabled(false);
        }
        else if(state == State.themQL){
            setEnableQL(true);            
            jTable3.setEnabled(false);
        }
        else if(state == State.suaQL){
            setEnableQL(true);            
            jTable3.setEnabled(false);
        }
    }
    private void setEnableBandoc(boolean b){
        txtbdten.setEnabled(b);
        txtbddc.setEnabled(b);
        txtbddt.setEnabled(b);
        btcapnhatbd.setEnabled(b);
        btboquabd.setEnabled(b);        
    }
    private void setEnableSach(boolean b){
        txtsten.setEnabled(b);
        txtstgia.setEnabled(b);
        txtsnam.setEnabled(b);
        txtssl.setEnabled(b);
        btcapnhats.setEnabled(b);
        btboquas.setEnabled(b);
    }
    private void setEnableQL(boolean b){
      txtqlmsl.setEnabled(b);      
      combomabd.setEnabled(b);
      combomas.setEnabled(b);
      btcapnhatqlm.setEnabled(b);
      btboquaqlm.setEnabled(b);
   }
    private void taoSachNam(){
        combo3.removeAllItems();
        Collections.sort(sach, new Comparator<Sach>(){            
            @Override
            public int compare(Sach t, Sach t1) {
                int n1=t.getNam(),n2=t1.getNam();
                if(n1 < n2){
                    return 1;
                } 
                if(n1 == n2){
                    return  0;
                }
                return -1;
            }
            });
        ArrayList<Integer> temp = new ArrayList<Integer>();
        ArrayList<Integer> tt = new ArrayList<Integer>();
        for(Sach i : sach){
            temp.add(i.getNam());
        }
        for(Integer i:temp){
            if(temp.contains(i)&&!tt.contains(i))
                tt.add(i);
        }
        for(Integer i:tt)
            combo3.addItem(Integer.toString(i));
    }
    private void taoSach(){
        DefaultTableModel m = (DefaultTableModel)jTable2.getModel();
        m.getDataVector().removeAllElements();
        for(Sach i : sach){
            m.addRow(i.toObject());
        }
        IOFile.outFile(sach, "sach.dat");
    }
    private void taoSach1(){
        DefaultTableModel m = (DefaultTableModel)jTable5.getModel();
        m.getDataVector().removeAllElements();
        for(Sach i : sach){
            m.addRow(i.toObject());
        }
        IOFile.outFile(sach, "sach.dat");
    }
    private void loadSach(){
        for(BangQuanLy i : qlmuon){
            for(Sach j : sach){
                if(i.getMaSach() == j.getMa()){
                    i.setSach(j);
                }
            }
        }
    }
    private Sach newSach(){
        Sach s = null;
        try{
            String ten = txtsten.getText(),tacGia = txtstgia.getText();
            int nam = Integer.parseInt(txtsnam.getText()),soLuong = Integer.parseInt(txtssl.getText());
            
            //int check = comboscn.getSelectedIndex();
            String chuyenNghanh = (String)comboscn.getSelectedItem() ;
//            if(check == 0){
//                chuyenNghanh = "Khoa Hoc Tu nhien";
//            }
//            if(check == 1){
//                chuyenNghanh = "Van Hoa - Nghe Thhuat";
//            }
//            if(check == 2){
//                chuyenNghanh = "Dien Tu Vien Thong";
//            }
//            if(check == 3){
//                chuyenNghanh = "Cong Nghe Thong Tin";
//            }
            if(ten.equals("") || tacGia.equals("")){
                JOptionPane.showMessageDialog(this,"khong duoc bo trong du lieu");
            } else if(nam < 0 || soLuong < 0){
                JOptionPane.showMessageDialog(this,"nam hoa so luong phai la so nguyen duong");
            } else {
                int ma = Integer.parseInt(txtsma.getText());
                s = new Sach(ten, tacGia, chuyenNghanh, ma, nam, soLuong);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"nam va so luong la mot so nguyen duong");
        }
        return s;
    }
    private void themSach(){
        Sach s = newSach();
        if(s != null){
            sach.add(s);
            combomas.addItem(Integer.toString(s.getMa()));
            taoSach();            
        }
        txtsten.setText("");
        txtstgia.setText("");
        txtsnam.setText("");
        txtssl.setText("");        
    }

    private void suaSach(){
        DefaultTableModel m = (DefaultTableModel)jTable2.getModel();
        int check = jTable2.getSelectedRow();
            Sach s = newSach();
            sach.set(check, s);
            loadSach();
            taoSach();           
            txtsten.setText("");
            txtstgia.setText("");
            txtsnam.setText("");
            txtssl.setText("");
    }
    private void xoaSach(){
        DefaultTableModel m = (DefaultTableModel)jTable2.getModel();
        int check = jTable2.getSelectedRow();
        if((check < 0) || (check > jTable2.getRowCount())||(jTable2.getRowCount()==0)){
            JOptionPane.showMessageDialog(this,"hay chon doi tuong xoa!");
        } else {            
            sach.remove(check);
            combomas.removeItemAt(check);
            loadSach();
            taoSach();                     
        }
    }
    private boolean timSach(int ma){
        for(Sach s:sach)
            if(s.getMa()==ma)
                return true;
        return false;
    }
    private void taoBandoc(){
        DefaultTableModel m = (DefaultTableModel)jTable1.getModel();
        m.getDataVector().removeAllElements();
        for(BanDoc i : bandoc){
            m.addRow(i.toObject());
        }
        IOFile.outFile(bandoc,"bandoc.dat");
    }
    
    private BanDoc newBandoc(){
        BanDoc bd = null;
        try{
            String ten = txtbdten.getText(),diachi = txtbddc.getText();
            long sdt = Long.parseLong(txtbddt.getText());
            if(ten.equals("") || diachi.equals("")){
                JOptionPane.showMessageDialog(this,"khong bo trong!");
            } else if(sdt < 0 ){
                JOptionPane.showMessageDialog(this,"Dien thoai phai la so!");
            }else {
                int ma = Integer.parseInt(txtbdma.getText());
                bd = new BanDoc(ma, ten, diachi, sdt);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Dien thoai phai la so!");
        }
        return bd;
    }
    
    private void themBandoc(){
        BanDoc bd = newBandoc();
        if(bd != null){
            bandoc.add(bd);
            taoBandoc();
            combomabd.addItem(Integer.toString(bd.getMa()));            
            txtbdten.setText("");
            txtbddc.setText("");
            txtbddt.setText("");
        }
    }
    
    private void suaBandoc(){
        DefaultTableModel m = (DefaultTableModel)jTable1.getModel();
        int check = jTable1.getSelectedRow();        
            BanDoc bd = newBandoc();
            bandoc.set(check, bd);
            loadBandoc();
            taoBandoc();           
            txtbdten.setText("");
            txtbddc.setText("");
            txtbddt.setText("");
    }
    private void xoaBandoc(){
        DefaultTableModel m = (DefaultTableModel)jTable1.getModel();
        int check = jTable1.getSelectedRow();
        if(check < 0 || check > jTable1.getRowCount()||(jTable1.getRowCount()==0)){
            JOptionPane.showMessageDialog(this,"hay chon 1 doi tuong sua!");
        } else {           
            bandoc.remove(check);
            combomabd.removeItemAt(check);
            loadBandoc();
            taoBandoc();                   
        }
    }
    private void loadBandoc(){
        for(BangQuanLy i : qlmuon){
            for(BanDoc j : bandoc){
                if(i.getMaBanDoc() == j.getMa()){
                    i.setBanDoc(j);
                }
            }
        }
    }
    private boolean timBandoc(int ma){
        for(BanDoc b:bandoc)
            if(b.getMa()==ma)
                return true;
        return false;
    }
    private void taoQLmuon(){
        DefaultTableModel m = (DefaultTableModel)jTable3.getModel();
        m.getDataVector().removeAllElements();
        for(BangQuanLy i : qlmuon){
            m.addRow(i.toObject());
        }
        IOFile.outFile(qlmuon,"qlmuon.dat");
    }        
    
    private BangQuanLy newQLmuon(){
        BangQuanLy bql = null;                                
        try{ 
            int maBD = combomabd.getSelectedIndex(),maS = combomas.getSelectedIndex();    
            int num = Integer.parseInt(txtqlmsl.getText());
            if(num < 0){
                JOptionPane.showMessageDialog(this,"so luongla so nguyen duong!");                
            } else if(num > 10){
                JOptionPane.showMessageDialog(this,"khong duoc muon qua 10 cuon sach!");
            } else {                
                bql = new BangQuanLy(bandoc.get(maBD),sach.get(maS), num);
            }
            }catch(Exception e){
            JOptionPane.showMessageDialog(this,"nhap sai kieu du lieu!");
        }
        return bql;
    }
    
    private void themQLmuon(){
        BangQuanLy bql = newQLmuon();
        if(bql != null){
            qlmuon.add(bql);
            txtqlmsl.setText("");
            taoQLmuon();           
        }        
    }

    private void suaQLmuon(){
        DefaultTableModel m = (DefaultTableModel)jTable3.getModel();
        int check = jTable3.getSelectedRow();        
            BangQuanLy bql = newQLmuon();
            if(bql == null){
                return ;
            } else {
                qlmuon.set(check, bql);
                txtqlmsl.setText("");
                taoQLmuon();              
            }
    }
    private void xoaQLmuon(){
        DefaultTableModel m = (DefaultTableModel)jTable3.getModel();
        int check = jTable3.getSelectedRow();
        if(check < 0 || check > jTable3.getRowCount()||(jTable3.getRowCount()==0)){
            JOptionPane.showMessageDialog(this,"hay chon 1 doi tuong sua!");
        } else {
                qlmuon.remove(check);                
                taoQLmuon();            
            }        
    }
           
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btthembd = new javax.swing.JButton();
        btsuabd = new javax.swing.JButton();
        btxoabd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtbdten = new javax.swing.JTextField();
        txtbddc = new javax.swing.JTextField();
        txtbddt = new javax.swing.JTextField();
        btcapnhatbd = new javax.swing.JButton();
        btboquabd = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtbdma = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btthems = new javax.swing.JButton();
        btsuas = new javax.swing.JButton();
        btxoas = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtsten = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtstgia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btcapnhats = new javax.swing.JButton();
        btboquas = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtsnam = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtssl = new javax.swing.JTextField();
        comboscn = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtsma = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btthemqlm = new javax.swing.JButton();
        btsuaqlm = new javax.swing.JButton();
        btxoaqlm = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtqlmsl = new javax.swing.JTextField();
        btcapnhatqlm = new javax.swing.JButton();
        btboquaqlm = new javax.swing.JButton();
        combomabd = new javax.swing.JComboBox<>();
        combomas = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        ckb1 = new javax.swing.JCheckBox();
        ckb2 = new javax.swing.JCheckBox();
        btsx1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        combo2 = new javax.swing.JComboBox<>();
        bttk2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btsx2 = new javax.swing.JButton();
        rd1 = new javax.swing.JRadioButton();
        rd2 = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txttk = new javax.swing.JTextField();
        combo1 = new javax.swing.JComboBox<>();
        bttk1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        combo3 = new javax.swing.JComboBox<>();
        bttk3 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        ckb3 = new javax.swing.JCheckBox();
        ckb4 = new javax.swing.JCheckBox();
        ckb5 = new javax.swing.JCheckBox();
        ckb6 = new javax.swing.JCheckBox();
        ckb7 = new javax.swing.JCheckBox();
        jPanel12 = new javax.swing.JPanel();
        r1 = new javax.swing.JRadioButton();
        r2 = new javax.swing.JRadioButton();
        r3 = new javax.swing.JRadioButton();
        r4 = new javax.swing.JRadioButton();
        r5 = new javax.swing.JRadioButton();
        r6 = new javax.swing.JRadioButton();
        r7 = new javax.swing.JRadioButton();
        tk = new javax.swing.JTextField();
        btsx4 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        lb1 = new javax.swing.JLabel();
        btsx3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã bạn đọc", "Tên bạn đọc", "Địa chỉ", "Số điện thoai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btthembd.setText("Thêm mới");
        btthembd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthembdActionPerformed(evt);
            }
        });

        btsuabd.setText("Sửa");
        btsuabd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsuabdActionPerformed(evt);
            }
        });

        btxoabd.setText("Xóa");
        btxoabd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoabdActionPerformed(evt);
            }
        });

        jLabel1.setText("Họ và tên:");

        jLabel2.setText("Địa chỉ:");

        jLabel3.setText("Điện thoại:");

        btcapnhatbd.setText("Cập nhật");
        btcapnhatbd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcapnhatbdActionPerformed(evt);
            }
        });

        btboquabd.setText("Bỏ qua");
        btboquabd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btboquabdActionPerformed(evt);
            }
        });

        jLabel12.setText("Mã bạn đọc:");

        txtbdma.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(btthembd))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel12))))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btsuabd, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(btxoabd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtbdma, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtbddt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                    .addComponent(txtbddc, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtbdten, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btcapnhatbd, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                    .addComponent(btboquabd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btthembd)
                    .addComponent(btsuabd)
                    .addComponent(btxoabd))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btcapnhatbd))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtbdma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtbdten, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtbddc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtbddt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btboquabd)))
                .addGap(0, 149, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ban doc", jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Chuyên ngành", "Năm xuất bản", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        btthems.setText("Thêm mới");
        btthems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthemsActionPerformed(evt);
            }
        });

        btsuas.setText("Sửa");
        btsuas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsuasActionPerformed(evt);
            }
        });

        btxoas.setText("Xóa");
        btxoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoasActionPerformed(evt);
            }
        });

        jLabel4.setText("Tên sách:");

        jLabel5.setText("Tác giả:");

        jLabel6.setText("Chuyên ngành:");

        btcapnhats.setText("Cập nhật");
        btcapnhats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcapnhatsActionPerformed(evt);
            }
        });

        btboquas.setText("Bỏ qua");
        btboquas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btboquasActionPerformed(evt);
            }
        });

        jLabel7.setText("Năm xuất bản");

        jLabel8.setText("Số lượng:");

        comboscn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khoa hoc tu nhien", "Van hoc - Nghe thuat", "Dien tu vien thong", "Cong nghe thong tin" }));

        jLabel14.setText("Mã sách:");

        txtsma.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btthems)
                                .addGap(40, 40, 40)
                                .addComponent(btsuas, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(btxoas, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel5))
                                                .addGap(70, 70, 70)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtsten, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                                    .addComponent(txtstgia, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                                    .addComponent(txtsma)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel8))
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addGap(47, 47, 47)
                                                        .addComponent(txtssl, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGap(50, 50, 50)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(comboscn, 0, 174, Short.MAX_VALUE)
                                                            .addComponent(txtsnam))))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btcapnhats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btboquas, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(163, 163, 163))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btthems)
                    .addComponent(btsuas)
                    .addComponent(btxoas))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btcapnhats))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtsma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtsten, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtstgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(comboscn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btboquas)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtsnam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtssl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 104, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sach", jPanel2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã bạn đọc", "Tên bạn đọc", "Mã sách", "Tên sách", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        btthemqlm.setText("Thêm mới");
        btthemqlm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthemqlmActionPerformed(evt);
            }
        });

        btsuaqlm.setText("Sửa");
        btsuaqlm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsuaqlmActionPerformed(evt);
            }
        });

        btxoaqlm.setText("Xóa");
        btxoaqlm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoaqlmActionPerformed(evt);
            }
        });

        jLabel9.setText("Mã bạn đọc:");

        jLabel10.setText("Mã sách:");

        jLabel13.setText("Số lượng:");

        btcapnhatqlm.setText("Cập nhật");
        btcapnhatqlm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcapnhatqlmActionPerformed(evt);
            }
        });

        btboquaqlm.setText("Bỏ qua");
        btboquaqlm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btboquaqlmActionPerformed(evt);
            }
        });

        combomabd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        combomas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btthemqlm)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(jLabel9)
                        .addComponent(jLabel13)))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btsuaqlm, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btxoaqlm, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(combomabd, 0, 171, Short.MAX_VALUE)
                                .addComponent(combomas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtqlmsl, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btboquaqlm, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btcapnhatqlm, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btthemqlm)
                    .addComponent(btsuaqlm)
                    .addComponent(btxoaqlm))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(combomabd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(combomas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btcapnhatqlm)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtqlmsl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btboquaqlm)))))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quan ly muon", jPanel3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã bạn đọc", "Tên bạn đọc", "Mã sách", "Tên sách", "Số lượng"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 153, 0), null), "Sắp xếp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 51)));

        ckb1.setText("Mã bạn đọc");

        ckb2.setText("Mã sách");

        btsx1.setText("Sắp xếp");
        btsx1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsx1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckb2)
                    .addComponent(ckb1))
                .addGap(18, 18, 18)
                .addComponent(btsx1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ckb1)
                        .addGap(18, 18, 18)
                        .addComponent(ckb2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btsx1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 102, 0), null, null), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 0)));

        combo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dưới 2", "Từ 2 - 5", "Từ 5 - 7", "Trên 7" }));

        bttk2.setText("Tìm kiếm");
        bttk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttk2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(bttk2)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bttk2)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 153, 0), null), "Sắp xếp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 51)));

        btsx2.setText("Sắp xếp");
        btsx2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsx2ActionPerformed(evt);
            }
        });

        rd1.setText("Tên bạn đọc");

        rd2.setText("Tên sách");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rd2)
                    .addComponent(rd1))
                .addGap(18, 18, 18)
                .addComponent(btsx2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btsx2))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rd1)
                        .addGap(18, 18, 18)
                        .addComponent(rd2)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 153, 0), null, null), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 0)));

        jLabel11.setText("Nhập tìm:");

        combo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bttk1.setText("Tim kiem");
        bttk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttk1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttk, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(bttk1)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txttk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(bttk1)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tim kiem va sap xep", jPanel4);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 102, 0), null, null), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 0)));

        combo3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dưới 2", "Từ 2 - 5", "Từ 5 - 7", "Trên 7" }));

        bttk3.setText("Tìm kiếm");
        bttk3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttk3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(combo3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(bttk3)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(combo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bttk3)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 153, 0), null), "Sở thích", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 51)));

        ckb3.setText("KFC");
        ckb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckb3ActionPerformed(evt);
            }
        });

        ckb4.setText("Phở gà");

        ckb5.setText("Bơi lội");

        ckb6.setText("Tennis");

        ckb7.setText("Cafe vỉa hè");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckb7)
                    .addComponent(ckb6)
                    .addComponent(ckb5)
                    .addComponent(ckb4)
                    .addComponent(ckb3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ckb3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckb4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckb5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckb6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ckb7)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 153, 0), null), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 51)));

        buttonGroup2.add(r1);
        r1.setText("Mã sách");

        buttonGroup2.add(r2);
        r2.setText("Tên sách");

        buttonGroup2.add(r3);
        r3.setText("Tác giả");

        buttonGroup2.add(r4);
        r4.setText("Chuyên ngành");

        buttonGroup2.add(r5);
        r5.setText("Năm xuất bản");

        buttonGroup2.add(r6);
        r6.setText("số lượng");

        buttonGroup2.add(r7);
        r7.setText("Tất cả");

        btsx4.setText("Tìm");
        btsx4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsx4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(r7)
                            .addComponent(r6)
                            .addComponent(r5)
                            .addComponent(r3)
                            .addComponent(r2)
                            .addComponent(r4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(r1)
                        .addGap(18, 18, 18)
                        .addComponent(btsx4)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tk, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(tk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(r1)
                    .addComponent(btsx4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(r2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(r3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(r4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(r5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(r6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(r7)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Chuyên ngành", "Năm xuất bản", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        lb1.setText("Sở thích");

        btsx3.setText("Hiển thị");
        btsx3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsx3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btsx3))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lb1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(btsx3))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(lb1))
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 67, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Thêm", jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btthemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthemsActionPerformed
        changeState(State.themSach);
        int ma = sach.size()+10000;
        while(true){
            if(!timSach(ma)){
               txtsma.setText(ma+"");
               break;               
            }
            ma++;
        }
    }//GEN-LAST:event_btthemsActionPerformed

    private void btsuasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsuasActionPerformed
        if(jTable2.getSelectedRow() >= 0){
                changeState(State.suaSach);
            } else{
                JOptionPane.showMessageDialog(this,"Chon doi tuong de sua!");
            }
    }//GEN-LAST:event_btsuasActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        DefaultTableModel m = (DefaultTableModel)jTable2.getModel();
        int check = jTable2.getSelectedRow();
        txtsma.setText(m.getValueAt(check, 0).toString());
        txtsten.setText(m.getValueAt(check, 1).toString());
        txtstgia.setText(m.getValueAt(check, 2).toString());
        txtsnam.setText(m.getValueAt(check, 4).toString());
        txtssl.setText(m.getValueAt(check, 5).toString());
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel m = (DefaultTableModel)jTable1.getModel();
        int check = jTable1.getSelectedRow();
        txtbdma.setText(m.getValueAt(check, 0).toString());
        txtbdten.setText(m.getValueAt(check, 1).toString());
        txtbddc.setText(m.getValueAt(check, 2).toString());
        txtbddt.setText(m.getValueAt(check, 3).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        DefaultTableModel m = (DefaultTableModel)jTable3.getModel();
        int check = jTable3.getSelectedRow();
        txtqlmsl.setText(m.getValueAt(check, 4).toString());
    }//GEN-LAST:event_jTable3MouseClicked

    private void btthembdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthembdActionPerformed
        changeState(State.themBandoc);
        int ma = bandoc.size()+10000;
        while(true){
            if(!timBandoc(ma)){
               txtbdma.setText(ma+"");
               break;               
            }
            ma++;
        }       
    }//GEN-LAST:event_btthembdActionPerformed

    private void btsuabdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsuabdActionPerformed
        if(jTable1.getSelectedRow() >= 0){
                changeState(State.suaBandoc);
            } else{
                JOptionPane.showMessageDialog(this,"Chon doi tuong de sua!");
            }
    }//GEN-LAST:event_btsuabdActionPerformed

    private void btthemqlmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthemqlmActionPerformed
        changeState(State.themQL);
    }//GEN-LAST:event_btthemqlmActionPerformed

    private void btsuaqlmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsuaqlmActionPerformed
        if(jTable3.getSelectedRow() >= 0){
                changeState(State.suaQL);
            } else{
                JOptionPane.showMessageDialog(this,"Chon doi tuong de sua!");
            }
    }//GEN-LAST:event_btsuaqlmActionPerformed

    private void btxoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoasActionPerformed
        xoaSach();
    }//GEN-LAST:event_btxoasActionPerformed

    private void btxoabdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoabdActionPerformed
        xoaBandoc();
    }//GEN-LAST:event_btxoabdActionPerformed

    private void btxoaqlmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaqlmActionPerformed
        xoaQLmuon();
    }//GEN-LAST:event_btxoaqlmActionPerformed

    private void btcapnhatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcapnhatsActionPerformed
        if(state==State.themSach)
           themSach();
        else if(state==State.suaSach)
            suaSach();
        taoSachNam();
        changeState(State.normal);
    }//GEN-LAST:event_btcapnhatsActionPerformed

    private void btboquasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btboquasActionPerformed
        changeState(State.normal);
    }//GEN-LAST:event_btboquasActionPerformed

    private void btcapnhatbdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcapnhatbdActionPerformed
        if(state==State.themBandoc)
           themBandoc();
        else if(state==State.suaBandoc)
            suaBandoc();
        changeState(State.normal);
    }//GEN-LAST:event_btcapnhatbdActionPerformed

    private void btcapnhatqlmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcapnhatqlmActionPerformed
        if(state==State.themQL)
           themQLmuon();
        else if(state==State.suaQL)
            suaQLmuon();
        changeState(State.normal);
    }//GEN-LAST:event_btcapnhatqlmActionPerformed

    private void btboquabdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btboquabdActionPerformed
        changeState(State.normal);
    }//GEN-LAST:event_btboquabdActionPerformed

    private void btboquaqlmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btboquaqlmActionPerformed
        changeState(State.normal);
    }//GEN-LAST:event_btboquaqlmActionPerformed

    private void bttk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttk1ActionPerformed
       if(txttk.getText().trim().equals("") || combo1.getSelectedIndex() < 0){
            JOptionPane.showMessageDialog(null,"Nhap thong tin tim kiem!");
            return;
        }
        else{
           Search find = new Search();
           String key =  txttk.getText().trim();
           String flag = search[combo1.getSelectedIndex()];
           if(flag.equals("Ma ban doc")){
               if(find.searchByMabd(key,qlmuon)!= null){
                   tam.removeAll(tam);
                   tam.addAll(find.searchByMabd(key,qlmuon));
                   DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
                   m.getDataVector().removeAllElements();
                   for(BangQuanLy i: tam)                   
                     m.addRow(i.toObject()); 
               }
               else{
                   JOptionPane.showMessageDialog(null,"khong tim thay!!!");
               }
           }
            if(flag.equals("Ten ban doc")){
               if(find.searchByTenbd(key,qlmuon)!= null){
                   tam.removeAll(tam);
                   tam.addAll(find.searchByTenbd(key,qlmuon));
                   DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
                   m.getDataVector().removeAllElements();
                   for(BangQuanLy i: tam)                   
                     m.addRow(i.toObject());                   
               }
               else{
                   JOptionPane.showMessageDialog(null,"khong tim thay!!");
               }
           }
           if(flag.equals("Ma sach")){
               if(find.searchByMas(key,qlmuon)!= null){
                   tam.removeAll(tam);
                   tam.addAll(find.searchByMas(key,qlmuon));
                   DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
                   m.getDataVector().removeAllElements();
                   for(BangQuanLy i: tam)                   
                     m.addRow(i.toObject());
               }
               else{
                   JOptionPane.showMessageDialog(null,"khong tim thay!!");
               }
           }
           if(flag.equals("Ten sach")){
               if(find.searchByTens(key,qlmuon)!= null){
                   tam.removeAll(tam);
                   tam.addAll(find.searchByTens(key,qlmuon));
                   DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
                   m.getDataVector().removeAllElements();
                   for(BangQuanLy i: tam)                   
                     m.addRow(i.toObject());
               }
               else{
                   JOptionPane.showMessageDialog(null,"khong tim thay!!");
               }
           }
           if(flag.equals("Tat ca")){
               if(find.searchByBatky(key,qlmuon)!= null){
                   tam.removeAll(tam);
                   tam.addAll(find.searchByBatky(key,qlmuon));
                   DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
                   m.getDataVector().removeAllElements();
                   for(BangQuanLy i: tam)                   
                     m.addRow(i.toObject());
               }
               else{
                   JOptionPane.showMessageDialog(null,"khong tim thay!!");
               }
           }           
        }        
    }                                        

    private void bttimkiemActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
    }//GEN-LAST:event_bttk1ActionPerformed

    private void bttk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttk2ActionPerformed
        if(combo2.getSelectedIndex() < 0){
            JOptionPane.showMessageDialog(null,"chon thong tin tim kiem!");
            return;
        }
        else{     
           Search find = new Search(); 
           int index = combo2.getSelectedIndex();
           if((index==0)&&((find.searchBysl(0,2,qlmuon)!=null))){                    
                   tam.removeAll(tam);
                   tam.addAll(find.searchBysl(0,2,qlmuon));                                         
                   DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
                   m.getDataVector().removeAllElements();
                   for(BangQuanLy i: tam)                   
                     m.addRow(i.toObject());                
                }    
               else
                 if((index==1)&&((find.searchBysl(2,5,qlmuon)!=null))){                    
                   tam.removeAll(tam);
                   tam.addAll(find.searchBysl(2,5,qlmuon));
                   DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
                   m.getDataVector().removeAllElements();
                   for(BangQuanLy i: tam)                   
                     m.addRow(i.toObject()); 
               }
               else
                 if((index==2)&&((find.searchBysl(5,7,qlmuon)!=null))){                    
                   tam.removeAll(tam);
                   tam.addAll(find.searchBysl(5,7,qlmuon));
                   DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
                   m.getDataVector().removeAllElements();
                   for(BangQuanLy i: tam)                   
                     m.addRow(i.toObject()); 
               }
               else
                 if((index==3)&&((find.searchBysl(7,10,qlmuon)!=null))){                    
                   tam.removeAll(tam);
                   tam.addAll(find.searchBysl(7,10,qlmuon));
                   DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
                   m.getDataVector().removeAllElements();
                   for(BangQuanLy i: tam)                   
                     m.addRow(i.toObject()); 
               }
               else{
                   JOptionPane.showMessageDialog(null,"khong tim thay!!!");
               }
           }
    }//GEN-LAST:event_bttk2ActionPerformed

    private void btsx1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsx1ActionPerformed
        if(ckb1.isSelected() && ckb2.isSelected()){
            Collections.sort(qlmuon, new Comparator<BangQuanLy>(){
            public int compare(BangQuanLy t1,BangQuanLy t2){
                int s1 = t1.getMaBanDoc(),s2 = t2.getMaBanDoc();
                if(s1 < s2){
                    return 1;
                } 
                if(s1 == s2){
                    int m1 = t1.getMaSach(), m2 = t2.getMaSach();
                    if(m1 < m2){
                       return 1;
                    }else if(m1==m2)
                            return 0;
                          else
                            return -1;                    
                }
                return -1;
            }
            });
            DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
            m.getDataVector().removeAllElements();
            for(BangQuanLy i : qlmuon){
                m.addRow(i.toObject());
            }
        }else if(ckb1.isSelected()){
            Collections.sort(qlmuon, new Comparator<BangQuanLy>(){
            public int compare(BangQuanLy t1,BangQuanLy t2){
                int s1 = t1.getMaBanDoc(),s2 = t2.getMaBanDoc();
                if(s1 < s2){
                    return 1;
                } 
                if(s1 == s2){
                    return  0;
                }
                return -1;
            }
            });
            DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
            m.getDataVector().removeAllElements();
            for(BangQuanLy i : qlmuon){
                m.addRow(i.toObject());
            }
        }else if(ckb2.isSelected()){
            Collections.sort(qlmuon, new Comparator<BangQuanLy>(){
            public int compare(BangQuanLy t1,BangQuanLy t2){
                int s1 = t1.getMaSach(),s2 = t2.getMaSach();
                if(s1 < s2){
                    return 1;
                } 
                if(s1 == s2){
                    return  0;
                }
                return -1;
            }
            });
            DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
            m.getDataVector().removeAllElements();
            for(BangQuanLy i : qlmuon){
                m.addRow(i.toObject());
            }
        }else
           JOptionPane.showMessageDialog(null,"Chon muc sap xep!!");         
    }//GEN-LAST:event_btsx1ActionPerformed

    private void btsx2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsx2ActionPerformed
        if(rd1.isSelected()){
            Collections.sort(qlmuon, new Comparator<BangQuanLy>(){
            public int compare(BangQuanLy t1,BangQuanLy t2){
                String s1 = t1.getTenBanDoc().trim().substring(t1.getTenBanDoc().trim().lastIndexOf(" ")+1)
                +t1.getTenBanDoc().trim().substring(0, t1.getTenBanDoc().trim().lastIndexOf(" "));
                String s2 = t2.getTenBanDoc().trim().substring(t2.getTenBanDoc().trim().lastIndexOf(" ")+1)
                +t2.getTenBanDoc().trim().substring(0, t2.getTenBanDoc().trim().lastIndexOf(" "));
                int result = s1.compareToIgnoreCase(s2);
                return result;
            }
            });
            DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
            m.getDataVector().removeAllElements();
            for(BangQuanLy i : qlmuon){
                m.addRow(i.toObject());
            }
        }else
          if(rd2.isSelected()){
              Collections.sort(qlmuon, new Comparator<BangQuanLy>(){
              public int compare(BangQuanLy t1,BangQuanLy t2){
                return t1.getTenSach().compareToIgnoreCase(t2.getTenSach());
              }
              });
              DefaultTableModel m = (DefaultTableModel)jTable4.getModel();
              m.getDataVector().removeAllElements();
              for(BangQuanLy i : qlmuon){
                m.addRow(i.toObject());
              } 
           }else
              JOptionPane.showMessageDialog(null,"Chon muc sap xep!!"); 
    }//GEN-LAST:event_btsx2ActionPerformed

    private void bttk3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttk3ActionPerformed
           Search find = new Search();
           String key = (String) combo3.getSelectedItem();

               if(find.searchByNam(Integer.parseInt(key),sach)!= null){
                   tambo.removeAll(tambo);
                   tambo.addAll(find.searchByNam(Integer.parseInt(key),sach));
                   DefaultTableModel m = (DefaultTableModel)jTable5.getModel();
                   m.getDataVector().removeAllElements();
                   for(Sach i: tambo)                   
                     m.addRow(i.toObject()); 
               }
               else{
                   JOptionPane.showMessageDialog(null,"khong tim thay!!!");
               }
    }//GEN-LAST:event_bttk3ActionPerformed

    private void btsx3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsx3ActionPerformed
        Component[] components = jPanel11.getComponents();
        String mm="";
        for (Component c : components) {
          JCheckBox cb = (JCheckBox) c;
          if (cb.isSelected())
              mm=mm+cb.getText()+",";            
        }
        lb1.setText(mm);
    }//GEN-LAST:event_btsx3ActionPerformed

    private void btsx4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsx4ActionPerformed
        if(tk.getText().trim().equals("") || (buttonGroup2.getSelection()==null)){
            JOptionPane.showMessageDialog(null,"Nhap thong tin va lua chon muc tim kiem!");
            return;
        }
        else{   
           Search find = new Search();   
           String key =  tk.getText().trim();
           String chon = buttonGroup2.getSelection().getActionCommand();            
           if(find.searchBySach(chon,key,sach)!= null){
                   tambo.removeAll(tambo);
                   tambo.addAll(find.searchBySach(chon,key,sach));
                   DefaultTableModel m = (DefaultTableModel)jTable5.getModel();
                   m.getDataVector().removeAllElements();
                   for(Sach i: tambo)                   
                     m.addRow(i.toObject()); 
               }
               else{
                   JOptionPane.showMessageDialog(null,"khong tim thay!!!");
               }           
           }
    }//GEN-LAST:event_btsx4ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void ckb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckb3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckb3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
enum State {
    themBandoc,
    suaBandoc,
    normal,
    themSach,
    suaSach,
    themQL,
    suaQL,
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btboquabd;
    private javax.swing.JButton btboquaqlm;
    private javax.swing.JButton btboquas;
    private javax.swing.JButton btcapnhatbd;
    private javax.swing.JButton btcapnhatqlm;
    private javax.swing.JButton btcapnhats;
    private javax.swing.JButton btsuabd;
    private javax.swing.JButton btsuaqlm;
    private javax.swing.JButton btsuas;
    private javax.swing.JButton btsx1;
    private javax.swing.JButton btsx2;
    private javax.swing.JButton btsx3;
    private javax.swing.JButton btsx4;
    private javax.swing.JButton btthembd;
    private javax.swing.JButton btthemqlm;
    private javax.swing.JButton btthems;
    private javax.swing.JButton bttk1;
    private javax.swing.JButton bttk2;
    private javax.swing.JButton bttk3;
    private javax.swing.JButton btxoabd;
    private javax.swing.JButton btxoaqlm;
    private javax.swing.JButton btxoas;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JCheckBox ckb1;
    private javax.swing.JCheckBox ckb2;
    private javax.swing.JCheckBox ckb3;
    private javax.swing.JCheckBox ckb4;
    private javax.swing.JCheckBox ckb5;
    private javax.swing.JCheckBox ckb6;
    private javax.swing.JCheckBox ckb7;
    private javax.swing.JComboBox<String> combo1;
    private javax.swing.JComboBox<String> combo2;
    private javax.swing.JComboBox<String> combo3;
    private javax.swing.JComboBox<String> combomabd;
    private javax.swing.JComboBox<String> combomas;
    private javax.swing.JComboBox<String> comboscn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JLabel lb1;
    private javax.swing.JRadioButton r1;
    private javax.swing.JRadioButton r2;
    private javax.swing.JRadioButton r3;
    private javax.swing.JRadioButton r4;
    private javax.swing.JRadioButton r5;
    private javax.swing.JRadioButton r6;
    private javax.swing.JRadioButton r7;
    private javax.swing.JRadioButton rd1;
    private javax.swing.JRadioButton rd2;
    private javax.swing.JTextField tk;
    private javax.swing.JTextField txtbddc;
    private javax.swing.JTextField txtbddt;
    private javax.swing.JTextField txtbdma;
    private javax.swing.JTextField txtbdten;
    private javax.swing.JTextField txtqlmsl;
    private javax.swing.JTextField txtsma;
    private javax.swing.JTextField txtsnam;
    private javax.swing.JTextField txtssl;
    private javax.swing.JTextField txtsten;
    private javax.swing.JTextField txtstgia;
    private javax.swing.JTextField txttk;
    // End of variables declaration//GEN-END:variables
}
