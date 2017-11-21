/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.BangQuanLy;
import model.Sach;


public class Search {
    public ArrayList<BangQuanLy> searchByMabd (String s, ArrayList<BangQuanLy> list){
        ArrayList<BangQuanLy> ql = new ArrayList<BangQuanLy>();
         int check  = 0;
            for(BangQuanLy b : list){
                if(Integer.toString(b.getMaBanDoc()).indexOf(s)>= 0){
                    ql.add(b);
                    check = 1;
                }
            }
        if(check == 1){
            return ql;
        }
        else 
         return null;
    }
    public ArrayList<BangQuanLy> searchByTenbd (String s, ArrayList<BangQuanLy> list){
        ArrayList<BangQuanLy> ql = new ArrayList<BangQuanLy>();
         int check  = 0;
            for(BangQuanLy b : list){
                if (b.getTenBanDoc().indexOf(s)>= 0){
                    ql.add(b);
                    check = 1;
                }
            }
        if(check == 1){
            return ql;
        }
        else 
         return null;
    }
    public ArrayList<BangQuanLy> searchByMas (String s, ArrayList<BangQuanLy> list){
        ArrayList<BangQuanLy> ql = new ArrayList<BangQuanLy>();
         int check  = 0;
            for(BangQuanLy b : list){
                if(Integer.toString(b.getMaSach()).indexOf(s)>= 0){
                    ql.add(b);
                    check = 1;
                }
            }
        if(check == 1){
            return ql;
        }
        else 
         return null;
    }
    public ArrayList<BangQuanLy> searchByTens (String s, ArrayList<BangQuanLy> list){
        ArrayList<BangQuanLy> ql = new ArrayList<BangQuanLy>();
         int check  = 0;
            for(BangQuanLy b : list){
                if (b.getTenSach().indexOf(s)>= 0){
                    ql.add(b);
                    check = 1;
                }
            }
        if(check == 1){
            return ql;
        }
        else 
         return null;
    }    
    public ArrayList<BangQuanLy> searchByBatky (String s, ArrayList<BangQuanLy> list){
        ArrayList<BangQuanLy> ql = new ArrayList<BangQuanLy>();
         int check  = 0;
            for(BangQuanLy b : list){
                if ((Integer.toString(b.getMaBanDoc()).indexOf(s)>= 0)||(b.getTenBanDoc().indexOf(s)>= 0)||(Integer.toString(b.getMaSach()).indexOf(s)>= 0)||(b.getTenSach().indexOf(s)>= 0)){
                    ql.add(b);
                    check = 1;
                }
            }
        if(check == 1){
            return ql;
        }
        else 
         return null;
    }
    public ArrayList<BangQuanLy> searchBysl (int from,int to, ArrayList<BangQuanLy> list){
        ArrayList<BangQuanLy> ql = new ArrayList<BangQuanLy>();
         int check  = 0;
            for(BangQuanLy b : list){
                if ((b.getSoLuong()>=from) && (b.getSoLuong()<to)){
                    ql.add(b);
                    check = 1;
                }
            }
        if(check == 1){
            return ql;
        }
        else 
         return null;
    }
    public ArrayList<Sach> searchByNam (int nam, ArrayList<Sach> list){
        ArrayList<Sach> ql = new ArrayList<Sach>();
         int check  = 0;
            for(Sach b : list){
                if (b.getNam()==nam){
                    ql.add(b);
                    check = 1;
                }
            }
        if(check == 1){
            return ql;
        }
        else 
         return null;
    }
    public ArrayList<Sach> searchBySach (String r,String key, ArrayList<Sach> list){
         ArrayList<Sach> ql = new ArrayList<Sach>();
         int check  = 0;
         switch(r){
            case "r1":
                 for(Sach b : list){                                    
                   if (Integer.toString(b.getMa()).indexOf(key)>=0){
                      ql.add(b);
                      check = 1;
                }
                  
            }
                 break; 
            case "r2":
                 for(Sach b : list){                                    
                   if (b.getTen().indexOf(key)>=0){
                      ql.add(b);
                      check = 1;
                }
            }
                 break; 
            case "r3":
                 for(Sach b : list){                                    
                   if (b.getTacGia().indexOf(key)>=0){
                      ql.add(b);
                      check = 1;
                }
            }
                 break; 
            case "r4":
                 for(Sach b : list){                                    
                   if (b.getChuyenNghanh().indexOf(key)>=0){
                      ql.add(b);
                      check = 1;
                }
            }
                 break; 
            case "r5":
                 for(Sach b : list){                                    
                   if (Integer.toString(b.getNam()).indexOf(key)>=0){
                      ql.add(b);
                      check = 1;
                }
            }
                 break; 
            case "r6":
                 for(Sach b : list){                                    
                   if (Integer.toString(b.getSoLuong()).indexOf(key)>=0){
                      ql.add(b);
                      check = 1;
                }
            }
                 break; 
            case "r7":
                 for(Sach b : list){                                    
                   if (Integer.toString(b.getNam()).indexOf(key)>=0 || Integer.toString(b.getSoLuong()).indexOf(key)>=0 
                           || b.getChuyenNghanh().indexOf(key)>=0 || b.getTacGia().indexOf(key)>=0 ||b.getTen().indexOf(key)>=0
                          || Integer.toString(b.getMa()).indexOf(key)>=0){
                      ql.add(b);
                      check = 1;
                }
            }
                 break; 
            }
        if(check == 1){
            return ql;
        }
        else 
         return null;
    }
}