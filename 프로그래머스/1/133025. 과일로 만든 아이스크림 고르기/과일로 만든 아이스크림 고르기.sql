SELECT F.FLAVOR
FROM FIRST_HALF F
    JOIN ICECREAM_INFO I ON F.FLAVOR = I.FLAVOR
WHERE F.FLAVOR IN (
    SELECT FLAVOR
    FROM FIRST_HALF F
    WHERE F.TOTAL_ORDER > 3000
) AND I.INGREDIENT_TYPE = 'FRUIT_BASED'
ORDER BY F.TOTAL_ORDER DESC
;

