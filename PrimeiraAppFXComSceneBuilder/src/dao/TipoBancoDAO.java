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

import modelo.*;


public class TipoBancoDAO {
	
	public Conexao conexao = new Conexao();
	   public int incluiTipoBanco(Cliente c) throws SQLException
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
	        String desc=c.getSt_pv_nome().trim();
	        String cpf=c.getSt_pv_cpf().trim();
	        sql="select * from cliente where nome ='"+desc+"' or cpf ='"+cpf+"'";
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
	        	            sql="insert into cliente (nome,cpf,telefone) values (";		
	        	            sql+="'"+c.getSt_pv_nome()+"','"+c.getSt_pv_cpf()+"','"+c.getIn_pv_telefone()+" ')";		
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
    
   public Cliente buscaTipoBancoPorId(int codigo)
         throws SQLException {
	   Cliente a = new Cliente();
	   String sql=null;
	sql="select * from cliente where codigo = "+codigo;
	Connection conn= conexao.abreConexaoBD();
	ResultSet rs = null;
	Statement st=null;
	st = conn.createStatement();
	rs= st.executeQuery(sql);
	if (rs.next()==true)
	{

	    a.setIn_pv_codigo(rs.getInt("codigo"));
	    a.setSt_pv_nome(rs.getString("nome"));
	    a.setSt_pv_cpf(rs.getString("cpf"));
	    a.setIn_pv_telefone(rs.getInt("telefone"));
	    
	    }
	else {
		if(rs.next() == false) {
			JOptionPane.showMessageDialog(null, "Banco de dados não encontrado");
		}
//	   a=null;

	}
	   return a;
    }
    


   
   public int excluirTipoID(TipoBanco c) throws SQLException
   {
       //retorna 0=> erro na inclusÃ£o
       //        1=> incluiu
       //        2=> registro cadastrado
       int retorno=0;
       String sql=null;
       Connection conn= conexao.abreConexaoBD();
       ResultSet rs;
       Statement st = conn.createStatement();
       String desc=c.getTipoBanco_sigla().trim();
       System.out.println(c.getTipoBanco_id());
         sql="select * from tipos_bd where tip_sigla = "+"'"+c.getTipoBanco_sigla()+"'"+" and tip_id = "+c.getTipoBanco_id();
       
       rs = st.executeQuery(sql);
       if (rs.next()==false) {
                   retorno=0;
                   c.setRet(retorno);
           	//c.setMsg("Erro ao excluir");
       	}   
     /*  else		{
	            retorno=2; // possui cadastro
	            c.setMsg("dados não encotrados");
	            c.setRet(retorno);
       		}  */
	        if (retorno==0)		
	        {		
	        	sql="delete from tipos_bd where tip_id = "+c.getTipoBanco_id();	         
	        	int qR= st.executeUpdate(sql);		
	            if (qR==0) {		
	                retorno=0;	
	                c.setMsg("Erro ao excluir");
	                c.setRet(retorno);
	            }    else {		
	                retorno=1;
	                c.setMsg("Excluido registro");
	                c.setRet(retorno);
	            }
	        }		
	        return  c.getRet();
   }
   
   
   
   
   public int alterar(Cliente c) throws SQLException
   {
       //retorna 0=> erro na inclusÃ£o
       //        1=> incluiu
       //        2=> registro cadastrado
       int retorno=0;
       String sql=null;
       Connection conn= conexao.abreConexaoBD();
       ResultSet rs;
       Statement st = conn.createStatement();
       sql="select * from cliente where nome = "+"'"+c.getSt_pv_nome()+"'"+" and codigo = "+c.getIn_pv_codigo()+" and cpf = "
    		   +"'"+c.getSt_pv_cpf()+"'"+" and telefone = "+c.getIn_pv_telefone();
       
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
	        	 sql="update cliente set nome = "+"'"+c.getSt_pv_nome()+"'"+", cpf= "+"'"+c.getSt_pv_cpf()+"'"+",telefone ="
	        	 		+ "'"+c.getIn_pv_telefone()+"'"+" where codigo = "+c.getIn_pv_codigo();
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
   
   
   
   
   
   public List<Cliente> listar() {
       String sql = "SELECT * FROM cliente";
       List<Cliente> retorno = new ArrayList<>();
       try {
          // PreparedStatement stmt = connection.prepareStatement(sql);
          // ResultSet resultado = stmt.executeQuery();
           
           Connection conn= conexao.abreConexaoBD();
           ResultSet rs = null;
           Statement st=null;
           st = conn.createStatement();
           rs= st.executeQuery(sql);
           while (rs.next()) {
               Cliente a = new Cliente();
              // TipoBanco.setTipoBanco_id(rs.getInt("cdTipoBanco"));
               a.setSt_pv_nome(rs.getString("nome"));
               a.setSt_pv_cpf(rs.getString("cpf"));
               a.setIn_pv_telefone(rs.getInt("telefone"));
               a.setIn_pv_codigo(rs.getInt("codigo"));
               
               retorno.add(a);
               int b=0 ;
        
               
           }
           
           for (Cliente cliente : retorno) {
   			System.out.println(cliente.getSt_pv_nome());
          }

           
          // ;
       } catch (SQLException ex) {
  //         Logger.getLogger(TipoBancoDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
       return retorno;

   }

   
   
   
   
   public List<TipoBanco> teste() {
       String sql = "SELECT * FROM tipos_bd";
       List<TipoBanco> retorno = new ArrayList<>();
       try {
    	   Connection conn= conexao.abreConexaoBD();
           ResultSet rs = null;
           Statement st=null;
           st = conn.createStatement();
           rs= st.executeQuery(sql);
           while (rs.next()) {
        	   TipoBanco a = new TipoBanco();
        	   a.setTipoBanco_sigla(rs.getString("tip_sigla"));
               retorno.add(a);
           }
       } catch (SQLException ex) {
           Logger.getLogger(TipoBancoDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
       return retorno;
   }
   
   
   }


