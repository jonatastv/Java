package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import modelo.*;


public class FuncionarioDAO {
	
	public Conexao conexao = new Conexao();
	
	
	
	  public boolean login(String senha, String usuario )
		         throws SQLException {
		  Funcionario a = new Funcionario();
			   String sql=null;
			sql="select * from funcionario where usuario = '"+usuario+"'" +"and senha = '"+senha+"'"   ;
			Connection conn= conexao.abreConexaoBD();
			ResultSet rs = null;
			Statement st=null;
			st = conn.createStatement();
			rs= st.executeQuery(sql);
			try {
			if (rs.next()==true)
				
			{

			//    a.setIn_pv_codigo(rs.getInt("codigo"));
			    a.setSt_pv_usuario(rs.getString("usuario"));
			    a.setSt_pv_senha(rs.getString("senha"));
			    return true;
			    
			    }
			else {
				if(rs.next() == false) {
					JOptionPane.showMessageDialog(null, "Banco de dados não encontrado");
					return false;
				}
//			   a=null;

			}
			  
			}catch (Exception e) {
				 return false;
			} 
			return false;
		    }
		    

	
	
	
	
	   public int incluiTipoBanco(Servico c) throws SQLException
	    {
	        //retorna 0=> erro na inclusÃ£o
	        //        1=> incluiu
	        //        2=> registro cadastrado
	        int retorno=0;
	        c.setIn_pv_ret(retorno);
	        String sql=null;
	        
	        Connection conn= conexao.abreConexaoBD();
	        ResultSet rs;
	        Statement st = conn.createStatement();
	        String desc=c.getSt_pv_descricao().trim();
	      //  String cpf=c.getDb_pv_valor().trim();
	        sql="select * from servico where descricao ='"+desc+"'" ;
	        rs = st.executeQuery(sql);
	        if (rs.next()==false) {
	                    retorno=0;
	                    c.setIn_pv_ret(retorno);
	       // c.setMsg("Erro de Inclusão");
	        }     else		{
	        	            retorno=2; // possui cadastro
	        	            c.setSt_pv_msg("possui cadastro msg");
	        	            c.setIn_pv_ret(retorno);
	        }
	        	        if (retorno==0)		
	        	        {		
	        	            sql="insert into servico (descricao,valor,cor) values (";		
	        	            sql+="'"+c.getSt_pv_descricao()+"','"+c.getDb_pv_valor()+"','"+c.getSt_pv_cor()+" ')";		
	        	            int qRs= st.executeUpdate(sql);		
	        	            if (qRs==0) {		
	        	                retorno=0;	
	        	                c.setSt_pv_msg("Erro de Inclusão");
	        	                c.setIn_pv_ret(retorno);
	        	            }    else {		
	        	                retorno=1;
	        	                c.setSt_pv_msg("Inclusão realizada com sucesso");
	        	                c.setIn_pv_ret(retorno);
	        	            }
	        	        }		
	        	        return  c.getIn_pv_ret();
	    }
    
   public Servico buscaTipoBancoPorId(int codigo )
         throws SQLException {
	   Servico a = new Servico();
	   String sql=null;
	sql="select * from servico where codigo = "+codigo;
	Connection conn= conexao.abreConexaoBD();
	ResultSet rs = null;
	Statement st=null;
	st = conn.createStatement();
	rs= st.executeQuery(sql);
	if (rs.next()==true)
	{

	    a.setIn_pv_codigo(rs.getInt("codigo"));
	    a.setSt_pv_descricao(rs.getString("descricao"));
	    a.setDb_pv_valor(rs.getDouble("valor"));
	    a.setSt_pv_cor(rs.getString("cor"));
	    
	    }
	else {
		if(rs.next() == false) {
			JOptionPane.showMessageDialog(null, "Banco de dados não encontrado");
		}
//	   a=null;

	}
	   return a;
    }
    


   
   public int excluirTipoID(Servico c) throws SQLException
   {
       //retorna 0=> erro na inclusÃ£o
       //        1=> incluiu
       //        2=> registro cadastrado
       int retorno=0;
       String sql=null;
       Connection conn= conexao.abreConexaoBD();
       ResultSet rs;
       Statement st = conn.createStatement();
       String desc=c.getSt_pv_descricao().trim();
      // System.out.println(c.getTipoBanco_id());
         sql="select * from servico where descricao = "+"'"+c.getSt_pv_descricao()+"'"+" and codigo = "+c.getIn_pv_codigo();
       
       rs = st.executeQuery(sql);
       if (rs.next()==false) {
                   retorno=0;
                   c.setIn_pv_ret(retorno);
           	//c.setMsg("Erro ao excluir");
       	}   
     /*  else		{
	            retorno=2; // possui cadastro
	            c.setMsg("dados não encotrados");
	            c.setRet(retorno);
       		}  */
	        if (retorno==0)		
	        {		
	        	sql="delete from servico where codigo = "+c.getIn_pv_codigo();	         
	        	int qR= st.executeUpdate(sql);		
	            if (qR==0) {		
	                retorno=0;	
	                c.setSt_pv_msg("Erro ao excluir");
	                c.setIn_pv_ret(retorno);
	            }    else {		
	                retorno=1;
	                c.setSt_pv_msg("Excluido registro");
	                c.setIn_pv_ret(retorno);
	            }
	        }		
	        return  c.getIn_pv_ret();
   }
   
   
   
   
   public int alterar(Servico c) throws SQLException
   {
       //retorna 0=> erro na inclusÃ£o
       //        1=> incluiu
       //        2=> registro cadastrado
       int retorno=0;
       String sql=null;
       Connection conn= conexao.abreConexaoBD();
       ResultSet rs;
       Statement st = conn.createStatement();
       sql="select * from servico where descricao = "+"'"+c.getSt_pv_descricao()+"'"+" and codigo = "+c.getIn_pv_codigo()+" and valor = "
    		   +"'"+c.getDb_pv_valor()+"'"+" and cor = "+"'"+c.getSt_pv_cor()+"'";
       
       rs = st.executeQuery(sql);
       if (rs.next()==false) {
                   retorno=0;
                   c.setIn_pv_ret(retorno);
           //	c.setMsg("ERRO UPDATE");
       	}     else		{
	            retorno=2; // possui cadastro
	            c.setSt_pv_msg("Mesmo dados informados");
	            c.setIn_pv_ret(retorno);
       		}
	        if (retorno==0)		
	        {		
	        	 sql="update servico set descricao = "+"'"+c.getSt_pv_descricao()+"'"+", valor= "+"'"+c.getDb_pv_valor()+"'"+", cor ="
	        	 		+ "'"+c.getSt_pv_cor()+"'"+" where codigo = "+c.getIn_pv_codigo();
	            int qR= st.executeUpdate(sql);		
	            if (qR==0) {		
	                retorno=0;	
	                c.setSt_pv_msg("ERRO UPDATE");
	                c.setIn_pv_ret(retorno);
	            }    else {		
	                retorno=1;
	                c.setSt_pv_msg("Alterado registro");
	                c.setIn_pv_ret(retorno);
	            }
	        }		
	        return  c.getIn_pv_ret();
   }
   
   
   
   
   
   public List<Servico> listar() {
       String sql = "SELECT * FROM cliente";
       List<Servico> retorno = new ArrayList<>();
       try {
          // PreparedStatement stmt = connection.prepareStatement(sql);
          // ResultSet resultado = stmt.executeQuery();
           
           Connection conn= conexao.abreConexaoBD();
           ResultSet rs = null;
           Statement st=null;
           st = conn.createStatement();
           rs= st.executeQuery(sql);
           while (rs.next()) {
        	   Servico a = new Servico();
              // TipoBanco.setTipoBanco_id(rs.getInt("cdTipoBanco"));
               a.setSt_pv_descricao(rs.getString("descricao"));
               a.setDb_pv_valor(rs.getDouble("valor"));
             
               a.setIn_pv_codigo(rs.getInt("codigo"));
               
               retorno.add(a);
               int b=0 ;
        
               
           }
           
           for (Servico servico : retorno) {
   			System.out.println(servico.getSt_pv_descricao());
          }

           
          // ;
       } catch (SQLException ex) {
  //         Logger.getLogger(TipoBancoDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
       return retorno;

   }

   
   
   
   
   public List<Servico> teste() {
       String sql = "SELECT * FROM tipos_bd";
       List<Servico> retorno = new ArrayList<>();
       try {
    	   Connection conn= conexao.abreConexaoBD();
           ResultSet rs = null;
           Statement st=null;
           st = conn.createStatement();
           rs= st.executeQuery(sql);
           while (rs.next()) {
        	   Servico a = new Servico();
        	   a.setSt_pv_descricao(rs.getString("descricao"));
               retorno.add(a);
           }
       } catch (SQLException ex) {
           Logger.getLogger(TipoBancoDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
       return retorno;
   }
   
   
   }


