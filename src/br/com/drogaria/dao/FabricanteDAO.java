package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.HibernateUtil;

public class FabricanteDAO {

	public void salvar(Fabricante fabricante) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(fabricante);
			transacao.commit(); // Confirma transa��o.

		} catch (RuntimeException ex) {

			if (transacao != null) {
				transacao.rollback(); // Desfaz a transa��o caso entre no catch
			}

		} finally {
			sessao.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Fabricante> listar() {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Fabricante> fabricantes = null;

		try {
			Query consulta = sessao.getNamedQuery("Fabricante.listar");
			fabricantes = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return fabricantes;

	}
	
	@SuppressWarnings("unchecked")
	public Fabricante buscarPorCodigo(Long codigo) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Fabricante fabricante = null;

		try {
			Query consulta = sessao.getNamedQuery("Fabricante.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			
			fabricante = (Fabricante) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return fabricante;

	}

}
