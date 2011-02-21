import com.jrock.forum.groovy.util.HibernateUtil
import org.hibernate.Session
import com.jrock.forum.LegacyCar
import com.jrock.forum.LegacyTire
import com.jrock.forum.LegacyTirePK
/**
 * @author Bayo Erinle
 */

Session s = HibernateUtil.getSession()
s.beginTransaction()

long start = System.currentTimeMillis()
(1..5).each{ it ->
    LegacyCar c = new LegacyCar(id:"${it}", model:"model${it}")

//    c.addToTires(new LegacyTire(id:new LegacyTirePK(tireId:"a${it}", carId: "${c.id}")))
//    c.addToTires(new LegacyTire(id:new LegacyTirePK(tireId:"b${it}", carId: "${c.id}")))
//    c.addToTires(new LegacyTire(id:new LegacyTirePK(tireId:"c${it}", carId: "${c.id}")))
//    c.addToTires(new LegacyTire(id:new LegacyTirePK(tireId:"d${it}", carId: "${c.id}")))

    s.save(c)

    if( it % 20 == 0){
        s.flush()
        s.clear()

        println "progress ${it}"
    }
}
s.getTransaction().commit()
long end = System.currentTimeMillis()

println "done seeding => Total time taken : ${end - start} ms"
