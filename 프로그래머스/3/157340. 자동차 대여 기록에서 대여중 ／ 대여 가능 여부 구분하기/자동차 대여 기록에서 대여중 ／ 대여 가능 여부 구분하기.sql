SELECT CAR_ID,
    CASE
        WHEN max('2022-10-16' BETWEEN START_DATE AND END_DATE) = 1 THEN '대여중'
        ELSE '대여 가능'
    END AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY RH
GROUP BY CAR_ID
ORDER BY 1 DESC;