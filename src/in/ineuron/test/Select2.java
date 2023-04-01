package in.ineuron.test;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class Select2 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		Integer id = 4;
		try {

			session = HibernateUtil.getSession();

			Criteria criteria = session.createCriteria(Product.class);// HQL=>from in.ineuron.model.Product

			Criterion ge = Restrictions.ge("pcost", 10000);
			Criterion le = Restrictions.le("pcost", 20000);

			criteria.add(ge);
			criteria.add(le);

			List<Product> list = criteria.list();// HQL=> from in.ineuron.model.Product where price>=10000 and price<=20000

			list.forEach(System.out::println);

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
