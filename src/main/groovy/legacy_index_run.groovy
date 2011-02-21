/**
 * @author Bayo Erinle
 */

import org.hibernate.search.FullTextSession
import org.hibernate.search.Search
import com.jrock.forum.groovy.util.HibernateUtil
import com.jrock.forum.LegacyCarPlant


try {
    FullTextSession fts = Search.getFullTextSession(HibernateUtil.getSession())

    long start = System.currentTimeMillis()

    fts.beginTransaction()
    fts.createIndexer(LegacyCarPlant.class)
    .batchSizeToLoadObjects(30)
    .threadsForSubsequentFetching(8)
    .threadsToLoadObjects(4)
    .limitIndexedObjectsTo(1)
    .startAndWait()
    fts.getTransaction().commit()

    long end = System.currentTimeMillis()

    println "done indexing! Total time taken: ${end - start} ms"
} catch (ex) {
    ex.printStackTrace()
}
