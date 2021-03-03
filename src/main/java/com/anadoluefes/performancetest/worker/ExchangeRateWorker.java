package com.anadoluefes.performancetest.worker;

import lombok.SneakyThrows;

public class ExchangeRateWorker implements Runnable{

    @SneakyThrows
    @Override
    public void run() {
        Util util = new Util();


        util.sendDate(50,
                "exchangerate","http://hip-fi-route-hip-dev.apps.r7mikab1.westeurope.aroapp.io/hip-fi/router/camel/exchangerate/query",
                1000,"EXCHANGERATE");

    /*    for (int i = 1; i <50 ; i++) {

            String exchangerate = Util.readFile("exchangerate", String.valueOf(i%3));

            Map jsonNode = objectMapper.readValue(exchangerate, Map.class);
            HttpEntity entity = new HttpEntity(jsonNode, httpHeaders);
            System.out.print("EXCHANGERATE"+i+"\t\t");
            LocalDateTime startingdate = LocalDateTime.now();
            System.out.print(startingdate+"\t\t");
            restTemplate.exchange("http://hip-fi-route-hip-dev.apps.r7mikab1.westeurope.aroapp.io/hip-fi/router/camel/exchangerate/query", HttpMethod.POST, entity, String.class);
            LocalDateTime enddate = LocalDateTime.now();
            System.out.print(enddate+"\t\t");
            System.out.println(Duration.between(startingdate,enddate));
            Thread.sleep(TimeUnit.SECONDS.toSeconds(2));

        } */
    }
}
