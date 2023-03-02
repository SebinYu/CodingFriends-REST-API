package net.skhu.codingFriends.config.DBReplica;

import net.skhu.codingFriends.config.DBReplica.DataSourceType;
import net.skhu.codingFriends.config.DBReplica.ReplicationRoutingDataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ReplicationTest {

    private static final String Test_Method_Name = "determineCurrentLookupKey";

    @Test
    @DisplayName("쓰기_전용_트랜잭션_테스트")
    @Transactional(readOnly = false)
    void writeOnlyTransactionTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // given
        ReplicationRoutingDataSource replicationRoutingDataSource = new ReplicationRoutingDataSource();

        // when
        Method determineCurrentLookupKey = ReplicationRoutingDataSource.class.getDeclaredMethod(Test_Method_Name);
        determineCurrentLookupKey.setAccessible(true);

        DataSourceType dataSourceType = (DataSourceType) determineCurrentLookupKey
                .invoke(replicationRoutingDataSource);

        // then
        assertThat(dataSourceType).isEqualTo(DataSourceType.Master);
    }

    @Test
    @DisplayName("읽기_전용_트랜잭션_테스트")
    @Transactional(readOnly = true)
    void readOnlyTransactionTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // given
        ReplicationRoutingDataSource replicationRoutingDataSource = new ReplicationRoutingDataSource();

        // when
        Method determineCurrentLookupKey = ReplicationRoutingDataSource.class.getDeclaredMethod(Test_Method_Name);
        determineCurrentLookupKey.setAccessible(true);

        DataSourceType dataSourceType = (DataSourceType) determineCurrentLookupKey
                .invoke(replicationRoutingDataSource);

        // then
        assertThat(dataSourceType).isEqualTo(DataSourceType.Slave);

    }

}
