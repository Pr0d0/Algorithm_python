SELECT DISTINCT C.CAR_ID, C.CAR_TYPE, ROUND(DAILY_FEE * 30 * ((100 - DISCOUNT_RATE) / 100)) AS FEE
FROM CAR_RENTAL_COMPANY_CAR C
JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY RH ON C.CAR_ID = RH.CAR_ID
JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN DP ON C.CAR_TYPE = DP.CAR_TYPE 
WHERE C.CAR_TYPE IN ('세단', 'SUV') 
    AND C.CAR_ID NOT IN (SELECT CAR_ID 
                         FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                         WHERE NOT (END_DATE < '2022-11-01' OR '2022-11-30' < START_DATE))
       AND DP.DURATION_TYPE = '30일 이상'
HAVING FEE BETWEEN 500000 AND 1999999
ORDER BY 3 DESC, 2, 1 DESC
;