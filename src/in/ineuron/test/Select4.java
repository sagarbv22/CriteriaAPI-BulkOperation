package in.ineuron.test;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class Select4 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		Integer id = 4;
		try {

			session = HibernateUtil.getSession();

			Criteria criteria = session.createCriteria(Product.class);// FROM in.ineuron.Product

			ProjectionList projectionList = Projections.projectionList();

			projectionList.add(Projections.property("pname"));
			projectionList.add(Projections.property("pcost"));

			// SELECT pname,pcost FROM in.ineuron.Product
			criteria.setProjection(projectionList);

			Criterion c1 = Restrictions.between("pcost", 10000, 20000);

			criteria.add(c1);
			// SELECT pname,pcost FROM in.ineuron.Product WHERE pcost BETWEEN 1000 AND 20000

			List<Object[]> list = criteria.list();

			list.forEach(row -> {
				for (Object obj : row)
					System.out.print(obj + "\t");
				System.out.println();
			});

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
