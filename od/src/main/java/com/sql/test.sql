-- 题目来自于力扣和牛客以及互联网 不特别说明默认来自力扣

-- 2016投资数额
Create Table If Not Exists Insurance (pid int, tiv_2015 float, tiv_2016 float, lat float, lon float);

insert into Insurance (pid, tiv_2015, tiv_2016, lat, lon) values ('1', '10', '5', '10', '10');
insert into Insurance (pid, tiv_2015, tiv_2016, lat, lon) values ('2', '20', '20', '20', '20');
insert into Insurance (pid, tiv_2015, tiv_2016, lat, lon) values ('3', '10', '30', '20', '20');
insert into Insurance (pid, tiv_2015, tiv_2016, lat, lon) values ('4', '10', '40', '40', '40');

-- 执行超时了 todo
select sum(i.tiv_2016)
from Insurance i
where not exists(select 1 from Insurance as i2 where i2.lon = i.lon and i2.lat = i.lat and i2.pid <> i.pid)
  and exists(select 1 from Insurance as i1 where i1.tiv_2015 = i.tiv_2015 and i1.pid <> i.pid);

-- 上面联表太慢了 换in过了
select round(sum(tiv_2016), 2) as tiv_2016
from insurance
where tiv_2015 in (
  select tiv_2015
  from insurance
  group by tiv_2015
  having count(*) > 1
)
  and (lat, lon) not in (
  select lat, lon
  from insurance
  group by lat, lon
  having count(*) > 1
);

-- 小众书籍
Create table If Not Exists Books (book_id int, name varchar(50), available_from date);
Create table If Not Exists Orders (order_id int, book_id int, quantity int, dispatch_date date);

insert into Books (book_id, name, available_from) values ('1', 'Kalila And Demna', '2010-01-01');
insert into Books (book_id, name, available_from) values ('2', '28 Letters', '2012-05-12');
insert into Books (book_id, name, available_from) values ('3', 'The Hobbit', '2019-06-10');
insert into Books (book_id, name, available_from) values ('4', '13 Reasons Why', '2019-06-01');
insert into Books (book_id, name, available_from) values ('5', 'The Hunger Games', '2008-09-21');

insert into Orders (order_id, book_id, quantity, dispatch_date) values ('1', '1', '2', '2018-07-26');
insert into Orders (order_id, book_id, quantity, dispatch_date) values ('2', '1', '1', '2018-11-05');
insert into Orders (order_id, book_id, quantity, dispatch_date) values ('3', '3', '8', '2019-06-11');
insert into Orders (order_id, book_id, quantity, dispatch_date) values ('4', '4', '6', '2019-06-05');
insert into Orders (order_id, book_id, quantity, dispatch_date) values ('5', '4', '5', '2019-06-20');
insert into Orders (order_id, book_id, quantity, dispatch_date) values ('6', '5', '9', '2009-02-02');
insert into Orders (order_id, book_id, quantity, dispatch_date) values ('7', '5', '8', '2010-04-13');

with tmp as (select t.book_id, t.quantity, t.dispatch_date, t.name
             from (select abs(datediff(b.available_from, '2019-06-23'))              as dif,
                          b.book_id,
                          b.name,
                          if(o.quantity is null, 0, o.quantity)                      as quantity,
                          if(o.dispatch_date is null, '1970-01-01', o.dispatch_date) as dispatch_date
                   from Books b
                          left join Orders o on b.book_id = o.book_id) as t
             where t.dif >= 30)
select distinct v.name, v.book_id
from (select book_id, name, date_format(dispatch_date, '%Y') as year, sum(quantity)
      from tmp
      group by year, book_id) as v;

-- 当选者
Create table If Not Exists Candidate (id int, name varchar(255));
Create table If Not Exists Vote (id int, candidateId int);

insert into Candidate (id, name) values ('1', 'A');
insert into Candidate (id, name) values ('2', 'B');
insert into Candidate (id, name) values ('3', 'C');
insert into Candidate (id, name) values ('4', 'D');
insert into Candidate (id, name) values ('5', 'E');

insert into Vote (id, candidateId) values ('1', '4');
insert into Vote (id, candidateId) values ('2', '3');
insert into Vote (id, candidateId) values ('3', '4');

insert into Vote (id, candidateId) values ('1', '2');
insert into Vote (id, candidateId) values ('2', '4');
insert into Vote (id, candidateId) values ('3', '3');
insert into Vote (id, candidateId) values ('4', '2');
insert into Vote (id, candidateId) values ('5', '5');

-- 此sql有问题 下面的是正确的
select c.name
from Candidate as c,
     (select count(v.id) as count, e.id as u, rank() over (order by count(v.id) desc) as num
      from Vote v
             right join (select id from Vote) as e
                        on v.candidateId = e.id
      group by candidateId, e.id) as t
where c.id = t.u
  and t.num = 1;

select r.name as Name from(
select count(v.candidateId) as con, c.name
from Vote as v, Candidate as c
where v.candidateId = c.id
group by v.candidateId
order by con desc limit 1) as r;


-- 可疑银行账户
Create table If Not Exists Accounts (account_id int, max_income int);
Create table If Not Exists Transactions (transaction_id int, account_id int, type ENUM('creditor', 'debtor'), amount int, day datetime);

insert into Accounts (account_id, max_income) values ('3', '21000');
insert into Accounts (account_id, max_income) values ('4', '10400');

insert into Transactions (transaction_id, account_id, type, amount, day) values ('2', '3', 'Creditor', '107100', '2021-06-02 11:38:14');
insert into Transactions (transaction_id, account_id, type, amount, day) values ('4', '4', 'Creditor', '10400', '2021-06-20 12:39:18');
insert into Transactions (transaction_id, account_id, type, amount, day) values ('11', '4', 'Debtor', '58800', '2021-07-23 12:41:55');
insert into Transactions (transaction_id, account_id, type, amount, day) values ('1', '4', 'Creditor', '49300', '2021-05-03 16:11:04');
insert into Transactions (transaction_id, account_id, type, amount, day) values ('15', '3', 'Debtor', '75500', '2021-05-23 14:40:20');
insert into Transactions (transaction_id, account_id, type, amount, day) values ('10', '3', 'Creditor', '102100', '2021-06-15 10:37:16');
insert into Transactions (transaction_id, account_id, type, amount, day) values ('14', '4', 'Creditor', '56300', '2021-07-21 12:12:25');
insert into Transactions (transaction_id, account_id, type, amount, day) values ('19', '4', 'Debtor', '101100', '2021-05-09 15:21:49');
insert into Transactions (transaction_id, account_id, type, amount, day) values ('8', '3', 'Creditor', '64900', '2021-07-26 15:09:56');
insert into Transactions (transaction_id, account_id, type, amount, day) values ('7', '3', 'Creditor', '90900', '2021-06-14 11:23:07');

with tmp as (
select account_id,
       n.month - row_number() over (partition by n.account_id order by n.month) as i
from(
select tr.account_id,
       month(day) as month,
       (IF(sum(amount) > ac.max_income, 1, 0)) as sign
from Transactions as tr
    inner join Accounts as ac
        on ac.account_id = tr.account_id
where type = 'creditor'
group by account_id, month
order by account_id) as n where sign = 1)
select account_id, count(1) from tmp group by account_id, i;

-- 苹果和桔子
Create table If Not Exists Sales (sale_date date, fruit ENUM('apples', 'oranges'), sold_num int);
drop table Sales;

insert into Sales (sale_date, fruit, sold_num) values ('2020-05-01', 'apples', '10');
insert into Sales (sale_date, fruit, sold_num) values ('2020-05-01', 'oranges', '8');
insert into Sales (sale_date, fruit, sold_num) values ('2020-05-02', 'apples', '15');
insert into Sales (sale_date, fruit, sold_num) values ('2020-05-02', 'oranges', '15');
insert into Sales (sale_date, fruit, sold_num) values ('2020-05-03', 'apples', '20');
insert into Sales (sale_date, fruit, sold_num) values ('2020-05-03', 'oranges', '0');
insert into Sales (sale_date, fruit, sold_num) values ('2020-05-04', 'apples', '15');
insert into Sales (sale_date, fruit, sold_num) values ('2020-05-04', 'oranges', '16');
-- 两种解法
-- 1 这个sql有问题 当天如果没桔子可能就漏掉了 自连接这种情况可能用不了
select s1.sale_date,
       (s1.sold_num - s2.sold_num) as diff
from Sales s1
       inner join Sales s2
where s1.sale_date = s2.sale_date
  and s1.fruit != s2.fruit and s1.fruit = 'apples';

-- 2
select
  sale_date,
  apples_sold_num - oranges_sold_num diff
from (
       select
         date_format(sale_date,'%Y-%m-%d') sale_date,
         sum(case when fruit = 'apples' then sold_num else 0 end) apples_sold_num,
         sum(case when fruit = 'oranges' then sold_num else 0 end) oranges_sold_num
       from Sales
       group by sale_date
     ) as a
order by sale_date;

-- 树节点
Create table If Not Exists Tree (id int, p_id int);
drop table Tree;
insert into Tree (id, p_id) values ('1', null);
insert into Tree (id, p_id) values ('2', '1');
insert into Tree (id, p_id) values ('3', '1');
insert into Tree (id, p_id) values ('4', '2');
insert into Tree (id, p_id) values ('5', '2');
insert into Tree (id, p_id) values ('6', '3');
-- 1
select id, 'Leaf' as Type from Tree where id not in (select p_id from Tree where p_id is not null);
select id,
       (case when a.p_id is null then 'Root'
             when a.p_id is not null and exists(select 1 from Tree b where a.id = b.p_id) then 'Inner'
             else 'Leaf'
         end) as Type
from Tree a;
-- 2
select id, 'Root' as Type from Tree where p_id is null
union
select distinct t1.id, 'Inner' as Type
from Tree t1
       inner join Tree t2
                  on t1.p_id is not null
                    and t2.p_id is not null
where t1.id = t2.p_id
union
select id, 'Leaf' as Type from Tree where id not in (select p_id from Tree where p_id is not null);

-- 二级关注者
Create table If Not Exists Follow (followee varchar(255), follower varchar(255));
drop table Follow;
insert into Follow (followee, follower) values ('Alice', 'Bob');
insert into Follow (followee, follower) values ('Bob', 'Cena');
insert into Follow (followee, follower) values ('Bob', 'Donald');
insert into Follow (followee, follower) values ('Donald', 'Edward');
-- 1
select f2.followee, count(f2.follower)
from Follow f1
       inner join Follow f2
                  on f1.follower = f2.followee group by f2.followee;
-- 2
select
  followee follower,
  count(*) num
from Follow
where followee in (
  select follower from Follow
)
group by followee
order by followee;

-- 换座位
Create table If Not Exists Seat (id int, student varchar(255));

insert into Seat (id, student) values ('1', 'Abbot');
insert into Seat (id, student) values ('2', 'Doris');
insert into Seat (id, student) values ('3', 'Emerson');
insert into Seat (id, student) values ('4', 'Green');
insert into Seat (id, student) values ('5', 'Jeames');
-- 1
select (
         case
           when s1.id % 2 = 0
        then s1.id - 1
           when s1.id = (select count(1) from Seat)
             then s1.id
           when s1.id % 2 <> 0
        then s1.id + 1
           end) as id,
       s1.student
from Seat s1 order by id;
-- 2
select
  case when mod(a.id,2)=0 then a.id-1
       when mod(a.id,2)=1 and a.id+1 <= (select max(id) from Seat) then a.id+1
       else a.id
    end id,
  a.student
from Seat a
order by 1;
-- 3
select if(t.id % 2 <> 0, if(t.id = (select count(distinct id) from seat), id, id + 1), t.id - 1) as id, t.student from seat as t order by id;

-- 买下所有产品的客户
Create table If Not Exists Customer (customer_id int, product_key int);
Create table If Not Exists Product (product_key int);

insert into Customer (customer_id, product_key) values ('1', '5');
insert into Customer (customer_id, product_key) values ('2', '6');
insert into Customer (customer_id, product_key) values ('3', '5');
insert into Customer (customer_id, product_key) values ('3', '6');
insert into Customer (customer_id, product_key) values ('1', '6');

insert into Product (product_key) values ('5');
insert into Product (product_key) values ('6');

select *, count(product_key) from (select distinct * from Customer) c where c.product_key in (select * from Product) group by customer_id;

-- 最后一个进入电梯的人
Create table If Not Exists Queue (person_id int, person_name varchar(30), weight int, turn int);
drop table Queue;
insert into Queue (person_id, person_name, weight, turn) values ('5', 'Alice', '250', '1');
insert into Queue (person_id, person_name, weight, turn) values ('4', 'Bob', '175', '5');
insert into Queue (person_id, person_name, weight, turn) values ('3', 'Alex', '350', '2');
insert into Queue (person_id, person_name, weight, turn) values ('6', 'John Cena', '400', '3');
insert into Queue (person_id, person_name, weight, turn) values ('1', 'Winston', '500', '6');
insert into Queue (person_id, person_name, weight, turn) values ('2', 'Marie', '200', '4');

select *
from (select sum(weight) over(order by turn) as weight, person_id,
             person_name,
             turn
      from Queue) as t
where t.weight = 1000;

-- 统计每个国家每个月的交易数据
Create table If Not Exists Transactions (id int, country varchar(4), state enum('approved', 'declined'), amount int, trans_date date);

insert into Transactions (id, country, state, amount, trans_date) values ('121', 'US', 'approved', '1000', '2018-12-18');
insert into Transactions (id, country, state, amount, trans_date) values ('122', 'US', 'declined', '2000', '2018-12-19');
insert into Transactions (id, country, state, amount, trans_date) values ('123', 'US', 'approved', '2000', '2019-01-01');
insert into Transactions (id, country, state, amount, trans_date) values ('124', 'DE', 'approved', '2000', '2019-01-07');

select date_format(t.trans_date, '%Y-%m'), t.id, t.state, t.amount,t.country from Transactions as t;

-- 统计每个国家每个月的交易数据2
Create table If Not Exists Transactions (id int, country varchar(4), state enum('approved', 'declined'), amount int, trans_date date);
Create table If Not Exists Chargebacks (trans_id int, trans_date date);

insert into Transactions (id, country, state, amount, trans_date) values ('101', 'US', 'approved', '1000', '2019-05-18');
insert into Transactions (id, country, state, amount, trans_date) values ('102', 'US', 'declined', '2000', '2019-05-19');
insert into Transactions (id, country, state, amount, trans_date) values ('103', 'US', 'approved', '3000', '2019-06-10');
insert into Transactions (id, country, state, amount, trans_date) values ('104', 'US', 'declined', '4000', '2019-06-13');
insert into Transactions (id, country, state, amount, trans_date) values ('105', 'US', 'approved', '5000', '2019-06-15');

insert into Chargebacks (trans_id, trans_date) values ('102', '2019-05-29');
insert into Chargebacks (trans_id, trans_date) values ('101', '2019-06-30');
insert into Chargebacks (trans_id, trans_date) values ('105', '2019-09-18');

with temp as (
  select t.id, t.country, t.state, t.amount, t.trans_date from Transactions t where t.state = 'approved'
  union
  select t.id, t.country, 'declined', t.amount, c.trans_date from Transactions t inner join Chargebacks c where t.id = c.trans_id
)
select date_format(t.trans_date, '%Y-%m') as month,
       t.country,
        count(IF(t.state = 'approved' ,1 , null)) as approved_count,
        sum(IF(t.state = 'approved', amount, 0)) as approved_amount,
        count(case when state = 'declined' then 1 else null end) chargeback_count,
        sum(case when state = 'declined' then amount else 0 end) chargeback_amount
from temp as t group by month,country;

-- 最近的三笔订单
Create table If Not Exists Customers (customer_id int, name varchar(10));
Create table If Not Exists Orders (order_id int, order_date date, customer_id int, cost int);

insert into Customers (customer_id, name) values ('1', 'Winston');
insert into Customers (customer_id, name) values ('2', 'Jonathan');
insert into Customers (customer_id, name) values ('3', 'Annabelle');
insert into Customers (customer_id, name) values ('4', 'Marwan');
insert into Customers (customer_id, name) values ('5', 'Khaled');

insert into Orders (order_id, order_date, customer_id, cost) values ('1', '2020-07-31', '1', '30');
insert into Orders (order_id, order_date, customer_id, cost) values ('2', '2020-7-30', '2', '40');
insert into Orders (order_id, order_date, customer_id, cost) values ('3', '2020-07-31', '3', '70');
insert into Orders (order_id, order_date, customer_id, cost) values ('4', '2020-07-29', '4', '100');
insert into Orders (order_id, order_date, customer_id, cost) values ('5', '2020-06-10', '1', '1010');
insert into Orders (order_id, order_date, customer_id, cost) values ('6', '2020-08-01', '2', '102');
insert into Orders (order_id, order_date, customer_id, cost) values ('7', '2020-08-01', '3', '111');
insert into Orders (order_id, order_date, customer_id, cost) values ('8', '2020-08-03', '1', '99');
insert into Orders (order_id, order_date, customer_id, cost) values ('9', '2020-08-07', '2', '32');
insert into Orders (order_id, order_date, customer_id, cost) values ('10', '2020-07-15', '1', '2');

select *
from (select rank() over (partition by o.customer_id order by o.order_date desc) as ranks, o.order_date,
             c.name,
             o.customer_id,
             o.cost
      from Orders o
             inner join Customers c on c.customer_id = o.customer_id) t
where ranks <= 3;

-- 联赛信息统计
Create table If Not Exists Teams (team_id int, team_name varchar(20));
Create table If Not Exists Matches (home_team_id int, away_team_id int, home_team_goals int, away_team_goals int);

insert into Teams (team_id, team_name) values ('1', 'Ajax');
insert into Teams (team_id, team_name) values ('4', 'Dortmund');
insert into Teams (team_id, team_name) values ('6', 'Arsenal');

insert into Matches (home_team_id, away_team_id, home_team_goals, away_team_goals) values ('1', '4', '0', '1');
insert into Matches (home_team_id, away_team_id, home_team_goals, away_team_goals) values ('1', '6', '3', '3');
insert into Matches (home_team_id, away_team_id, home_team_goals, away_team_goals) values ('4', '1', '5', '2');
insert into Matches (home_team_id, away_team_id, home_team_goals, away_team_goals) values ('6', '1', '0', '0');


with temp as (
  select m.home_team_id,
         m.away_team_id,
         m.home_team_goals,
         m.away_team_goals
  from Matches m
  union
  select
    m.away_team_id as home_team_id,
    m.home_team_id as away_team_id,
    m.away_team_goals as home_team_goals,
    m.home_team_goals as away_team_goals
  from Matches m
) select t.team_id,
         t.team_name,
         count(t.team_id) as played_count,
         sum(case
               when r.goals = 0 then 1
               when r.goals < 0 then 0
               when r.goals > 0 then 3 else 0 end) as points,
         sum(r.team_goals) as goals_for,
         sum(r.away_team_goals) as goals_away,
         sum(r.team_goals) - sum(r.away_team_goals) as goals_diff
from Teams as t inner join(
  select home_team_id as team_id,
         away_team_id,
         home_team_goals as team_goals,
         away_team_goals,
         home_team_goals - away_team_goals as goals
  from temp order by home_team_id) as r on t.team_id = r.team_id group by t.team_id, t.team_name order by goals_diff desc;

-- 页面推荐
Create table If Not Exists Friendship (user1_id int, user2_id int);
Create table If Not Exists Likes (user_id int, page_id int);

insert into Friendship (user1_id, user2_id) values ('1', '2');
insert into Friendship (user1_id, user2_id) values ('1', '3');
insert into Friendship (user1_id, user2_id) values ('1', '4');
insert into Friendship (user1_id, user2_id) values ('2', '3');
insert into Friendship (user1_id, user2_id) values ('2', '4');
insert into Friendship (user1_id, user2_id) values ('2', '5');
insert into Friendship (user1_id, user2_id) values ('6', '1');

insert into Likes (user_id, page_id) values ('1', '88');
insert into Likes (user_id, page_id) values ('2', '23');
insert into Likes (user_id, page_id) values ('3', '24');
insert into Likes (user_id, page_id) values ('4', '56');
insert into Likes (user_id, page_id) values ('5', '11');
insert into Likes (user_id, page_id) values ('6', '33');
insert into Likes (user_id, page_id) values ('2', '77');
insert into Likes (user_id, page_id) values ('3', '77');
insert into Likes (user_id, page_id) values ('6', '88');

with temp as (
  select (case when user2_id = '1' then user1_id else user2_id end) as user_fri,
         (case when user1_id <> '1' then user2_id else user1_id end) as user_id
  from Friendship where user1_id = '1' or user2_id = '1'
)
select distinct page_id from Likes as l inner join temp as t on l.user_id = t.user_fri where page_id not in (select page_id from Likes where user_id = '1');

-- 每件商品的最新订单
Create table If Not Exists Customers (customer_id int, name varchar(10));
Create table If Not Exists Orders (order_id int, order_date date, customer_id int, product_id int);
Create table If Not Exists Products (product_id int, product_name varchar(20), price int);

insert into Customers (customer_id, name) values ('1', 'Winston');
insert into Customers (customer_id, name) values ('2', 'Jonathan');
insert into Customers (customer_id, name) values ('3', 'Annabelle');
insert into Customers (customer_id, name) values ('4', 'Marwan');
insert into Customers (customer_id, name) values ('5', 'Khaled');

insert into Orders (order_id, order_date, customer_id, product_id) values ('1', '2020-07-31', '1', '1');
insert into Orders (order_id, order_date, customer_id, product_id) values ('2', '2020-7-30', '2', '2');
insert into Orders (order_id, order_date, customer_id, product_id) values ('3', '2020-08-29', '3', '3');
insert into Orders (order_id, order_date, customer_id, product_id) values ('4', '2020-07-29', '4', '1');
insert into Orders (order_id, order_date, customer_id, product_id) values ('5', '2020-06-10', '1', '2');
insert into Orders (order_id, order_date, customer_id, product_id) values ('6', '2020-08-01', '2', '1');
insert into Orders (order_id, order_date, customer_id, product_id) values ('7', '2020-08-01', '3', '1');
insert into Orders (order_id, order_date, customer_id, product_id) values ('8', '2020-08-03', '1', '2');
insert into Orders (order_id, order_date, customer_id, product_id) values ('9', '2020-08-07', '2', '3');
insert into Orders (order_id, order_date, customer_id, product_id) values ('10', '2020-07-15', '1', '2');

insert into Products (product_id, product_name, price) values ('1', 'keyboard', '120');
insert into Products (product_id, product_name, price) values ('2', 'mouse', '80');
insert into Products (product_id, product_name, price) values ('3', 'screen', '600');
insert into Products (product_id, product_name, price) values ('4', 'hard disk', '450');

select *
from (select p.product_name,
             o.product_id,
             o.order_id,
             o.customer_id,
             o.order_date,
             dense_rank() over (partition by p.product_name order by o.order_date desc) as rn
      from Orders o
             inner join
           Products p
           on p.product_id = o.product_id) as t
where rn = 1
order by t.product_name, t.product_id, t.order_id;

-- 游戏玩法分析 IV
Create table If Not Exists Activity (player_id int, device_id int, event_date date, games_played int);

insert into Activity (player_id, device_id, event_date, games_played) values ('1', '2', '2016-03-01', '5');
insert into Activity (player_id, device_id, event_date, games_played) values ('1', '2', '2016-03-02', '6');
insert into Activity (player_id, device_id, event_date, games_played) values ('2', '3', '2017-06-25', '1');
insert into Activity (player_id, device_id, event_date, games_played) values ('3', '1', '2016-03-02', '0');
insert into Activity (player_id, device_id, event_date, games_played) values ('3', '4', '2018-07-03', '5');

with
  tmp as (
    select
      a.player_id,
      a.event_date,
      row_number() over(partition by a.player_id order by a.event_date) rn
    from Activity a
  )
select
  round(
      (
        select
          count(1) cnt_activity
        from tmp a
               inner join Activity b
                          on a.player_id = b.player_id
                            and date_add(a.event_date,interval 1 day) = b.event_date
        where a.rn = 1
      )/(select count(1) cnt_all from tmp where rn = 1)
    ,2
    ) as fraction;

-- 最繁忙的机场
Create table If Not Exists Flights (departure_airport int, arrival_airport int, flights_count int);

insert into Flights (departure_airport, arrival_airport, flights_count) values ('1', '2', '4');
insert into Flights (departure_airport, arrival_airport, flights_count) values ('2', '1', '5');
insert into Flights (departure_airport, arrival_airport, flights_count) values ('2', '4', '5');

with temp as (select departure_airport, arrival_airport, flights_count
              from Flights
              union
              select arrival_airport as departure_airport, departure_airport as arrival_airport, flights_count
              from Flights)
select departure_airport, sum(flights_count)
from temp
group by departure_airport;
use
rjd;
select s.*, s1.name
from sanguo as s
       left join sanguo as s1 on s.manger_id = s1.z_id;

-- 学生地理信息 通过开窗去限制行转列
Create table If Not Exists StudentSTy (name varchar(50), continent varchar(7));

insert into StudentSTy (name, continent) values ('Jane', 'America');
insert into StudentSTy (name, continent) values ('Pascal', 'Europe');
insert into StudentSTy (name, continent) values ('Xi', 'Asia');
insert into StudentSTy (name, continent) values ('Jack', 'America');

select
  *,
  row_number() over(partition by continent order by name) as rn
from StudentSTy;
drop table StudentSTy;

with temp as (
  select s.*,
         (case
            when s.continent = 'Asia' then  '3'
            when s.continent = 'Europe' then '2'
            when s.continent = 'America' then '1'
           end) as r
  from StudentSTy as s)
select *
from (
       select
         IF(r = 1, name, null) as America,
         IF(r = 2, name, null) as Europe,
         IF(r = 3, name, null) as Asia
       from temp) as y;

-- 这题牛客的
-- 29 用户次日做题留存率
drop table if exists `user_profile`;
drop table if exists `question_practice_detail`;
drop table if exists `question_detail`;
CREATE TABLE `user_profile`
(
  `id`                    int         NOT NULL,
  `device_id`             int         NOT NULL,
  `gender`                varchar(14) NOT NULL,
  `age`                   int,
  `university`            varchar(32) NOT NULL,
  `gpa`                   float,
  `active_days_within_30` int,
  `question_cnt`          int,
  `answer_cnt`            int
);
CREATE TABLE `question_practice_detail`
(
  `id`          int         NOT NULL,
  `device_id`   int         NOT NULL,
  `question_id` int         NOT NULL,
  `result`      varchar(32) NOT NULL,
  `date`        date        NOT NULL
);
CREATE TABLE `question_detail`
(
  `id`              int         NOT NULL,
  `question_id`     int         NOT NULL,
  `difficult_level` varchar(32) NOT NULL
);

INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学',3.4,7,2,12);
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学',4.0,15,5,25);
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学',3.2,12,3,30);
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学',3.6,5,1,2);
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学',3.8,20,15,70);
INSERT INTO user_profile VALUES(6,2131,'male',28,'山东大学',3.3,15,7,13);
INSERT INTO user_profile VALUES(7,4321,'male',28,'复旦大学',3.6,9,6,52);
INSERT INTO question_practice_detail VALUES(1,2138,111,'wrong','2021-05-03');
INSERT INTO question_practice_detail VALUES(2,3214,112,'wrong','2021-05-09');
INSERT INTO question_practice_detail VALUES(3,3214,113,'wrong','2021-06-15');
INSERT INTO question_practice_detail VALUES(4,6543,111,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(5,2315,115,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(6,2315,116,'right','2021-08-14');
INSERT INTO question_practice_detail VALUES(7,2315,117,'wrong','2021-08-15');
INSERT INTO question_practice_detail VALUES(8,3214,112,'wrong','2021-05-09');
INSERT INTO question_practice_detail VALUES(9,3214,113,'wrong','2021-08-15');
INSERT INTO question_practice_detail VALUES(10,6543,111,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(11,2315,115,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(12,2315,116,'right','2021-08-14');
INSERT INTO question_practice_detail VALUES(13,2315,117,'wrong','2021-08-15');
INSERT INTO question_practice_detail VALUES(14,3214,112,'wrong','2021-08-16');
INSERT INTO question_practice_detail VALUES(15,3214,113,'wrong','2021-08-18');
INSERT INTO question_practice_detail VALUES(16,6543,111,'right','2021-08-13');
INSERT INTO question_detail VALUES(1,111,'hard');
INSERT INTO question_detail VALUES(2,112,'medium');
INSERT INTO question_detail VALUES(3,113,'easy');
INSERT INTO question_detail VALUES(4,115,'easy');
INSERT INTO question_detail VALUES(5,116,'medium');
INSERT INTO question_detail VALUES(6,117,'easy');

with temp as (
  select distinct q.device_id,
                  q.date
  from question_practice_detail as q group by device_id, q.date)
select count(t2.date) / count(t1.date)
from temp as t1
       left join temp t2
                 on t1.device_id = t2.device_id
                   and date_add(t1.date, interval 1 day) = t2.date;
-- 牛客的题
-- 31 还是 30 忘了
drop table if exists `user_profile`;
drop table if exists `question_practice_detail`;
drop table if exists `question_detail`;
CREATE TABLE `user_profile`
(
  `id`                    int         NOT NULL,
  `device_id`             int         NOT NULL,
  `gender`                varchar(14) NOT NULL,
  `age`                   int,
  `university`            varchar(32) NOT NULL,
  `gpa`                   float,
  `active_days_within_30` int,
  `question_cnt`          int,
  `answer_cnt`            int
);
CREATE TABLE `question_practice_detail`
(
  `id`          int         NOT NULL,
  `device_id`   int         NOT NULL,
  `question_id` int         NOT NULL,
  `result`      varchar(32) NOT NULL,
  `date`        date        NOT NULL
);
CREATE TABLE `question_detail`
(
  `id`              int         NOT NULL,
  `question_id`     int         NOT NULL,
  `difficult_level` varchar(32) NOT NULL
);

INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学',3.4,7,2,12);
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学',4.0,15,5,25);
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学',3.2,12,3,30);
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学',3.6,5,1,2);
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学',3.8,20,15,70);
INSERT INTO user_profile VALUES(6,2131,'male',28,'山东大学',3.3,15,7,13);
INSERT INTO user_profile VALUES(7,4321,'male',28,'复旦大学',3.6,9,6,52);
INSERT INTO question_practice_detail VALUES(1,2138,111,'wrong','2021-05-03');
INSERT INTO question_practice_detail VALUES(2,3214,112,'wrong','2021-05-09');
INSERT INTO question_practice_detail VALUES(3,3214,113,'wrong','2021-06-15');
INSERT INTO question_practice_detail VALUES(4,6543,111,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(5,2315,115,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(6,2315,116,'right','2021-08-14');
INSERT INTO question_practice_detail VALUES(7,2315,117,'wrong','2021-08-15');
INSERT INTO question_practice_detail VALUES(8,3214,112,'wrong','2021-05-09');
INSERT INTO question_practice_detail VALUES(9,3214,113,'wrong','2021-08-15');
INSERT INTO question_practice_detail VALUES(10,6543,111,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(11,2315,115,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(12,2315,116,'right','2021-08-14');
INSERT INTO question_practice_detail VALUES(13,2315,117,'wrong','2021-08-15');
INSERT INTO question_practice_detail VALUES(14,3214,112,'wrong','2021-08-16');
INSERT INTO question_practice_detail VALUES(15,3214,113,'wrong','2021-08-18');
INSERT INTO question_practice_detail VALUES(16,6543,111,'right','2021-08-13');
INSERT INTO question_detail VALUES(1,111,'hard');
INSERT INTO question_detail VALUES(2,112,'medium');
INSERT INTO question_detail VALUES(3,113,'easy');
INSERT INTO question_detail VALUES(4,115,'easy');
INSERT INTO question_detail VALUES(5,116,'medium');
INSERT INTO question_detail VALUES(6,117,'easy');

with tep as (select s.*,
                    (case
                       when result = 'wrong' then 1
                       when result = 'right' then 2
                       when result is null then 0
                      end
                      ) as right_count
             from (
                    select u.device_id,
                           u.university,
                           u.question_cnt,
                           q.result,
                           q.date,
                      month(q.date) as month
                    from question_practice_detail as q
                      right join user_profile as u
                    on u.device_id = q.device_id and q.device_id
                    where u.university = '复旦大学') as s
             where s.month = 8 or s.month is null)
select t.device_id,
       t.university,
       count(result) as count,
        (select count(result) from tep where right_count = 2) as right_c
from tep as t group by device_id;

-- 牛客的题 浙大不同题目的正确率
drop table if exists `user_profile`;
drop table if  exists `question_practice_detail`;
drop table if  exists `question_detail`;
CREATE TABLE `user_profile`
(
  `id`                    int         NOT NULL,
  `device_id`             int         NOT NULL,
  `gender`                varchar(14) NOT NULL,
  `age`                   int,
  `university`            varchar(32) NOT NULL,
  `gpa`                   float,
  `active_days_within_30` int,
  `question_cnt`          int,
  `answer_cnt`            int
);
CREATE TABLE `question_practice_detail`
(
  `id`          int         NOT NULL,
  `device_id`   int         NOT NULL,
  `question_id` int         NOT NULL,
  `result`      varchar(32) NOT NULL,
  `date`        date        NOT NULL
);
CREATE TABLE `question_detail`
(
  `id`              int         NOT NULL,
  `question_id`     int         NOT NULL,
  `difficult_level` varchar(32) NOT NULL
);

INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学',3.4,7,2,12);
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学',4.0,15,5,25);
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学',3.2,12,3,30);
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学',3.6,5,1,2);
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学',3.8,20,15,70);
INSERT INTO user_profile VALUES(6,2131,'male',28,'山东大学',3.3,15,7,13);
INSERT INTO user_profile VALUES(7,4321,'male',28,'复旦大学',3.6,9,6,52);
INSERT INTO question_practice_detail VALUES(1,2138,111,'wrong','2021-05-03');
INSERT INTO question_practice_detail VALUES(2,3214,112,'wrong','2021-05-09');
INSERT INTO question_practice_detail VALUES(3,3214,113,'wrong','2021-06-15');
INSERT INTO question_practice_detail VALUES(4,6543,111,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(5,2315,115,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(6,2315,116,'right','2021-08-14');
INSERT INTO question_practice_detail VALUES(7,2315,117,'wrong','2021-08-15');
INSERT INTO question_practice_detail VALUES(8,3214,112,'wrong','2021-05-09');
INSERT INTO question_practice_detail VALUES(9,3214,113,'wrong','2021-08-15');
INSERT INTO question_practice_detail VALUES(10,6543,111,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(11,2315,115,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(12,2315,116,'right','2021-08-14');
INSERT INTO question_practice_detail VALUES(13,2315,117,'wrong','2021-08-15');
INSERT INTO question_practice_detail VALUES(14,3214,112,'wrong','2021-08-16');
INSERT INTO question_practice_detail VALUES(15,3214,113,'wrong','2021-08-18');
INSERT INTO question_practice_detail VALUES(16,6543,111,'right','2021-08-13');
INSERT INTO question_detail VALUES(1,111,'hard');
INSERT INTO question_detail VALUES(2,112,'medium');
INSERT INTO question_detail VALUES(3,113,'easy');
INSERT INTO question_detail VALUES(4,115,'easy');
INSERT INTO question_detail VALUES(5,116,'medium');
INSERT INTO question_detail VALUES(6,117,'easy');

with temp as (
  select
    difficult_level,
    (case
       when result = 'right' then 1
       when result = 'wrong' then 0
      end
      ) as r_result,
    result
  from question_detail as qd inner join (
    select up.university, q.*
    from question_practice_detail as q
           inner join user_profile up
                      on q.device_id = up.device_id and up.university = '浙江大学') as t
                                        on qd.question_id = t.question_id)
select difficult_level,
       sum(r_result) / count(1) from temp group by difficult_level;

-- 大满贯数量
Create table If Not Exists Players (player_id int, player_name varchar(20));
Create table If Not Exists Championships (year int, Wimbledon int, Fr_open int, US_open int, Au_open int);

insert into Players (player_id, player_name) values ('1', 'Nadal');
insert into Players (player_id, player_name) values ('2', 'Federer');
insert into Players (player_id, player_name) values ('3', 'Novak');

insert into Championships (year, Wimbledon, Fr_open, US_open, Au_open) values ('2018', '1', '1', '1', '1');
insert into Championships (year, Wimbledon, Fr_open, US_open, Au_open) values ('2019', '1', '1', '2', '2');
insert into Championships (year, Wimbledon, Fr_open, US_open, Au_open) values ('2020', '2', '1', '2', '2');

select p.player_id,p.player_name,
       sum((case when c.Wimbledon = p.player_id then 1 else 0 end)+
           (case when c.Fr_open = p.player_id then 1 else 0 end)+
           (case when c.US_open = p.player_id then 1 else 0 end)+
           (case when c.Au_open = p.player_id then 1 else 0 end)) as count
from Championships c inner join Players p group by p.player_id;

-- 绘制绘画柱状图
Create table If Not Exists Sessions (session_id int, duration int);

insert into Sessions (session_id, duration) values ('1', '30');
insert into Sessions (session_id, duration) values ('2', '199');
insert into Sessions (session_id, duration) values ('3', '299');
insert into Sessions (session_id, duration) values ('4', '580');
insert into Sessions (session_id, duration) values ('5', '1000');

with temp as (select '[0 -> 5' as flag
              union
              select '[5 -> 10' as flag
              union
              select '[10 -> 15' as flag
              union
              select '[15 or more' as flag)
select y.flag, count(t.flag)
from temp as y
       left join (select session_id,
                         duration / 60,
                         (case
                            when duration / 60 < 5 then '[0 -> 5'
                            when duration / 60 >= 5 and duration / 60 < 10 then '[5 -> 10'
                            when duration / 60 >= 10 and duration / 60 < 15 then '[10 -> 15'
                            when duration / 60 >= 15 then '[15 or more' end) as flag
                  from Sessions) as t on t.flag = y.flag
group by t.flag;

select t.flag,
       count(t.flag)
from (select session_id,
             duration / 60,
             (case
                when duration / 60 < 5 then '[0 -> 5'
                when duration / 60 >= 5 and duration / 60 < 10 then '[5 -> 10'
                when duration / 60 >= 10 and duration / 60 < 15 then '[10 -> 15'
                when duration / 60 >= 15 then '[15 or more' end
               ) as flag
      from Sessions) as t
group by flag;

-- 净现值查询
Create Table If Not Exists NPV (id int, year int, npv int);
Create Table If Not Exists Queries (id int, year int);

insert into NPV (id, year, npv) values ('1', '2018', '100');
insert into NPV (id, year, npv) values ('7', '2020', '30');
insert into NPV (id, year, npv) values ('13', '2019', '40');
insert into NPV (id, year, npv) values ('1', '2019', '113');
insert into NPV (id, year, npv) values ('2', '2008', '121');
insert into NPV (id, year, npv) values ('3', '2009', '21');
insert into NPV (id, year, npv) values ('11', '2020', '99');
insert into NPV (id, year, npv) values ('7', '2019', '0');

insert into Queries (id, year) values ('1', '2019');
insert into Queries (id, year) values ('2', '2008');
insert into Queries (id, year) values ('3', '2009');
insert into Queries (id, year) values ('7', '2018');
insert into Queries (id, year) values ('7', '2019');
insert into Queries (id, year) values ('7', '2020');
insert into Queries (id, year) values ('13', '2019');

select Q.id, Q.year, if(N.npv is null, 0, N.npv)
from Queries as Q
       left join NPV N
                 on Q.year = N.year
                   and Q.id = N.id;

-- 遗失的id 递归查 难理解 mysql8.0以上支持
Create table If Not Exists Customers (customer_id int, customer_name varchar(20));

insert into Customers (customer_id, customer_name) values ('1', 'Alice');
insert into Customers (customer_id, customer_name) values ('4', 'Bob');
insert into Customers (customer_id, customer_name) values ('5', 'Charlie');

with recursive temp as (
  select 1 as customer_id from Customers
  union
  select customer_id + 1 from temp where customer_id < (select max(customer_id) from Customers)
) select * from temp where customer_id not in (select customer_id from Customers);


-- 账户余额
Create table If Not Exists Transactions (account_id int, day date, type ENUM('Deposit', 'Withdraw'), amount int);

insert into Transactions (account_id, day, type, amount) values ('1', '2021-11-07', 'Deposit', '2000');
insert into Transactions (account_id, day, type, amount) values ('1', '2021-11-09', 'Withdraw', '1000');
insert into Transactions (account_id, day, type, amount) values ('1', '2021-11-11', 'Deposit', '3000');
insert into Transactions (account_id, day, type, amount) values ('2', '2021-12-07', 'Deposit', '7000');
insert into Transactions (account_id, day, type, amount) values ('2', '2021-12-12', 'Withdraw', '7000');

select s.*,
       sum(amount) over (partition by account_id order by day) as balance
from (
       select t.account_id, day, type,
         (case
         when t.type = 'Deposit' then t.amount
         when t.type = 'Withdraw' then -t.amount
         end
         ) as amount
       from Transactions as t) as s;

Select @sum:=0;
select t.account_id, day, type,
  (case
  when t.type = 'Deposit' then (select @sum:= @sum + t.amount)
  when t.type = 'Withdraw' then (select @sum:= @sum - t.amount)
  end
  ) as amount
from Transactions as t where account_id = 2;

-- 成绩处于中游的学生
Create table If Not Exists Student (student_id int, student_name varchar(30));
Create table If Not Exists Exam (exam_id int, student_id int, score int);

insert into Student (student_id, student_name) values ('1', 'Daniel');
insert into Student (student_id, student_name) values ('2', 'Jade');
insert into Student (student_id, student_name) values ('3', 'Stella');
insert into Student (student_id, student_name) values ('4', 'Jonathan');
insert into Student (student_id, student_name) values ('5', 'Will');

insert into Exam (exam_id, student_id, score) values ('10', '1', '70');
insert into Exam (exam_id, student_id, score) values ('10', '2', '80');
insert into Exam (exam_id, student_id, score) values ('10', '3', '90');
insert into Exam (exam_id, student_id, score) values ('20', '1', '80');
insert into Exam (exam_id, student_id, score) values ('30', '1', '70');
insert into Exam (exam_id, student_id, score) values ('30', '3', '80');
insert into Exam (exam_id, student_id, score) values ('30', '4', '90');
insert into Exam (exam_id, student_id, score) values ('40', '1', '60');
insert into Exam (exam_id, student_id, score) values ('40', '2', '70');
insert into Exam (exam_id, student_id, score) values ('40', '4', '80');
-- 哪条是对的忘记了 等我再做一遍 答案有误 todo 待改正
select *,
       CEIL(examCount / 2 ) as middle,
       rank() over (partition by exam_id order by score) as rn1,
  rank() over (partition by exam_id order by score desc) as rn2
from (
       select S.student_name,
              e.*,
              count(exam_id) over (partition by e.student_id) as count,
       count(exam_id) over (partition by e.exam_id) as examCount
       from Exam as e right join Student S
       on e.student_id = S.student_id) as t where t.count > 0;

select distinct student_id, student_name
from (select *,
             count(student_id) over (partition by student_name) as final
      from (select *,
                   IF(rn1 > rn2, rn1 - rn2, - (rn2 - rn1)) as sign
            from (select *,
                         CEIL(examCount / 2) as middle,
                         rank()                 over (partition by exam_id order by score) as rn1, rank() over (partition by exam_id order by score desc) as rn2
                  from (select S.student_name,
                               e.*,
                               count(exam_id) over (partition by e.exam_id) as examCount, count(exam_id) over (partition by e.student_id) as count
                        from Exam as e inner join Student S
                        on e.student_id = S.student_id) as t) as r) as q
      where sign = 0) as y
where final = count;

-- 企业员工中位数
Create table If Not Exists Employee (id int, company varchar(255), salary int);

insert into Employee (id, company, salary) values ('1', 'A', '2341');
insert into Employee (id, company, salary) values ('2', 'A', '341');
insert into Employee (id, company, salary) values ('3', 'A', '15');
insert into Employee (id, company, salary) values ('4', 'A', '15314');
insert into Employee (id, company, salary) values ('5', 'A', '451');
insert into Employee (id, company, salary) values ('6', 'A', '513');
insert into Employee (id, company, salary) values ('7', 'B', '15');
insert into Employee (id, company, salary) values ('8', 'B', '13');
insert into Employee (id, company, salary) values ('9', 'B', '1154');
insert into Employee (id, company, salary) values ('10', 'B', '1345');
insert into Employee (id, company, salary) values ('11', 'B', '1221');
insert into Employee (id, company, salary) values ('12', 'B', '234');
insert into Employee (id, company, salary) values ('13', 'C', '2345');
insert into Employee (id, company, salary) values ('14', 'C', '2645');
insert into Employee (id, company, salary) values ('15', 'C', '2645');
insert into Employee (id, company, salary) values ('16', 'C', '2652');
insert into Employee (id, company, salary) values ('17', 'C', '65');

select * from (
                select t.*,
                       t.co % 2 as sign,
       FLOOR(t.co / 2) as signSub
                from (
                  select e.*,
                  count(salary) over (partition by company) as co,
                  dense_rank() over (partition by company order by salary) as rn1,
                  dense_rank() over (partition by company order by salary desc) as rn2
                  from Employee as e) as t) as s
where IF(sign = 0, rn1 in (signSub + 1 ,signSub), rn1 = signSub);

-- 职员雇佣
Create table If Not Exists Candidates (employee_id int, experience ENUM('Senior', 'Junior'), salary int);

insert into Candidates (employee_id, experience, salary) values ('1', 'Junior', '10000');
insert into Candidates (employee_id, experience, salary) values ('9', 'Junior', '15000');
insert into Candidates (employee_id, experience, salary) values ('2', 'Senior', '20000');
insert into Candidates (employee_id, experience, salary) values ('11', 'Senior', '16000');
insert into Candidates (employee_id, experience, salary) values ('13', 'Senior', '50000');
insert into Candidates (employee_id, experience, salary) values ('4', 'Junior', '40000');

set @max:= 0;

select @max:= min(e.salarySum)
from (
  select (70000 - sum (t.salary) over (order by t.salary)) as salarySum
  from Candidates as t
  where experience = 'Senior' group by employee_id) as e
where e.salarySum > 0
order by salarySum;

select *
from (select t.employee_id, t.experience, (70000 - sum(t.salary) over (order by t.salary)) as salarySum
      from Candidates as t
      where experience = 'Senior'
      group by employee_id) as e
where e.salarySum > 0
union
select *
from (select t.employee_id, t.experience, (@max - sum(t.salary) over (order by t.salary)) as salarySum
      from Candidates as t
      where experience = 'Junior'
      group by employee_id) as e
where e.salarySum > 0;

-- 寻找未通过的任务对
Create table If Not Exists Tasks (task_id int, subtasks_count int);
Create table If Not Exists Executed (task_id int, subtask_id int);

insert into Tasks (task_id, subtasks_count) values ('1', '3');
insert into Tasks (task_id, subtasks_count) values ('2', '2');
insert into Tasks (task_id, subtasks_count) values ('3', '4');

insert into Executed (task_id, subtask_id) values ('1', '2');
insert into Executed (task_id, subtask_id) values ('3', '1');
insert into Executed (task_id, subtask_id) values ('3', '2');
insert into Executed (task_id, subtask_id) values ('3', '3');
insert into Executed (task_id, subtask_id) values ('3', '4');

with
  recursive all_tasks as(
  select task_id,subtasks_count as subtask_id from Tasks
  union all
  select task_id,subtask_id - 1 as subtask_id from all_tasks where subtask_id > 1
)
select
  *
from all_tasks
where (task_id,subtask_id) not in (
  select task_id,subtask_id from Executed
);

with recursive temp as (
  select task_id, subtasks_count as subtask_id from Tasks
  union
  select task_id, subtask_id - 1 as subtask_id from temp where subtask_id > 1)
select * from temp where (task_id, subtask_id) not in (
  select task_id, subtask_id from Executed
);

-- 查询球队积分 写的又臭又长 通过name来过滤有点问题应该通过 team_id来过滤 主体没问题 需要小改一下 我改过提交过了 大家可以自己找找bug
Create table If Not Exists Teams (team_id int, team_name varchar(30));
Create table If Not Exists Matches (match_id int, host_team int, guest_team int, host_goals int, guest_goals int);

insert into Teams (team_id, team_name) values ('10', 'Leetcode FC');
insert into Teams (team_id, team_name) values ('20', 'NewYork FC');
insert into Teams (team_id, team_name) values ('30', 'Atlanta FC');
insert into Teams (team_id, team_name) values ('40', 'Chicago FC');
insert into Teams (team_id, team_name) values ('50', 'Toronto FC');
insert into Teams (team_id, team_name) values ('60', 'Nankatsu');

insert into Matches (match_id, host_team, guest_team, host_goals, guest_goals) values ('1', '10', '20', '3', '0');
insert into Matches (match_id, host_team, guest_team, host_goals, guest_goals) values ('2', '30', '10', '2', '2');
insert into Matches (match_id, host_team, guest_team, host_goals, guest_goals) values ('3', '10', '50', '5', '1');
insert into Matches (match_id, host_team, guest_team, host_goals, guest_goals) values ('4', '20', '30', '1', '0');
insert into Matches (match_id, host_team, guest_team, host_goals, guest_goals) values ('5', '50', '30', '1', '0');

select IF(sum(s.score) is null, 0, sum(s.score)) as soc,
       ts.team_id,
       ts.team_name
from (
       with temp as (
         select
           (select team_name from Teams where team_id = m.host_team) as host_name,
           (select team_name from Teams where team_id = m.guest_team) as guest_name,
           m.host_goals,
           m.guest_goals
         from Matches m
         union all
         select
           (select team_name from Teams where team_id = m.guest_team) as host_name,
           (select team_name from Teams where team_id = m.host_team) as guest_name,
           m.guest_goals as host_goals,
           m.host_goals as guest_goals
         from Matches m) select row_number() over (partition by host_name order by host_goals desc) as id,
         t.*,
                                (case
                                   when host_goals - guest_goals = 0 then 1
                                   when host_goals - guest_goals > 0 then 3
                                   else 0
                                  end) as score
       from temp t
     ) as s right join teams ts on ts.team_name = s.host_name group by host_name;


-- 访问日期之间的最大空挡期
Create table If Not Exists UserVisits(user_id int, visit_date date);

insert into UserVisits (user_id, visit_date) values ('1', '2020-11-28');
insert into UserVisits (user_id, visit_date) values ('1', '2020-10-20');
insert into UserVisits (user_id, visit_date) values ('1', '2020-12-3');
insert into UserVisits (user_id, visit_date) values ('2', '2020-10-5');
insert into UserVisits (user_id, visit_date) values ('2', '2020-12-9');
insert into UserVisits (user_id, visit_date) values ('3', '2020-11-11');

select r.*,
       datediff(r.ma, r.mi) as time
from (
  select user_id,
  max(visit_date) as ma,
  min(visit_date) as mi
  from UserVisits group by user_id) as r;

SELECT
  a.user_id,
  a.visit_date,
  lead(a.visit_date, 1, '2021-01-01') over (partition by a.user_id order by a.visit_date) as lead_visit_date
FROM UserVisits a;


-- 查询具有最多共同关注者的所有两两结对组 没拿下 止步于内连接 分组条件没想明白
Create table If Not Exists Relations (user_id int, follower_id int);

insert into Relations (user_id, follower_id) values ('1', '3');
insert into Relations (user_id, follower_id) values ('2', '3');
insert into Relations (user_id, follower_id) values ('7', '3');
insert into Relations (user_id, follower_id) values ('1', '4');
insert into Relations (user_id, follower_id) values ('2', '4');
insert into Relations (user_id, follower_id) values ('7', '4');
insert into Relations (user_id, follower_id) values ('1', '5');
insert into Relations (user_id, follower_id) values ('2', '6');
insert into Relations (user_id, follower_id) values ('7', '5');

drop table Relations

select u1, u2
from (select t.*,
             rank() over (order by t.count desc) as rk
      from (select count(*) as count, r1.user_id as u1, r2.user_id as u2
            from Relations as r1
              inner join Relations as r2
            on r1.user_id < r2.user_id and r1.follower_id = r2.follower_id
            group by r1.user_id, r2.user_id) as t limit 1) as y;


-- 报告系统状态的连续日期
-- 连续区间问题 先计算字段值在全表中的序号 此题就是日期的天数
--            再计算分组的数量 一般通过开窗函数计算 此题就是 ro字段
--            最后相减 利用两个增量相同减出来值一样就可以看出连续区间 最后按要求过滤即可 一般是函数或者行转列
Create table If Not Exists Failed (fail_date date);
Create table If Not Exists Succeeded (success_date date);

insert into Failed (fail_date) values ('2018-12-28');
insert into Failed (fail_date) values ('2018-12-29');
insert into Failed (fail_date) values ('2019-01-04');
insert into Failed (fail_date) values ('2019-01-05');

insert into Succeeded (success_date) values ('2018-12-30');
insert into Succeeded (success_date) values ('2018-12-31');
insert into Succeeded (success_date) values ('2019-01-01');
insert into Succeeded (success_date) values ('2019-01-02');
insert into Succeeded (success_date) values ('2019-01-03');
insert into Succeeded (success_date) values ('2019-01-06');

drop table Succeeded;
drop table Failed;

select y.status,
       max(date) as end,
       min(date) as begin,
       days - ro as sign -- 统计连续标志 可忽略
from (
select t.*,
       row_number() over (partition by status order by date) as ro,
       day(date) as days
from (
select 'success' as status, success_date as date
from Succeeded where success_date between '2019-01-01' and '2019-12-31'
union all
select 'failed' as status, fail_date as date
from Failed where fail_date between '2019-01-01' and '2019-12-31') as t) as y group by status, sign;


-- 好友申请 没判空 还有问题
Create table If Not Exists FriendRequest (sender_id int, send_to_id int, request_date date);
Create table If Not Exists RequestAccepted (requester_id int, accepter_id int, accept_date date);

insert into FriendRequest (sender_id, send_to_id, request_date) values ('1', '2', '2016/06/01');
insert into FriendRequest (sender_id, send_to_id, request_date) values ('1', '3', '2016/06/01');
insert into FriendRequest (sender_id, send_to_id, request_date) values ('1', '4', '2016/06/01');
insert into FriendRequest (sender_id, send_to_id, request_date) values ('2', '3', '2016/06/02');
insert into FriendRequest (sender_id, send_to_id, request_date) values ('3', '4', '2016/06/09');
insert into FriendRequest (sender_id, send_to_id, request_date) values (null, null, null);

insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('1', '2', '2016/06/03');
insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('1', '3', '2016/06/08');
insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('2', '3', '2016/06/08');
insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('3', '4', '2016/06/09');
insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('3', '4', '2016/06/10');

drop table FriendRequest;
drop table RequestAccepted;

-- 此sql是正确的 其实不需要通过id去关联确定 只是统计两表的比值
select round(IFNULL(
         (select count(distinct requester_id, accepter_id) from RequestAccepted) /
(select count(distinct sender_id, send_to_id) from FriendRequest)
,0),2) as accept_rate;


with temp as (
  select distinct requester_id as id1, accepter_id as id2, 'accept' as sign from RequestAccepted
  union all
  select distinct sender_id as id1, send_to_id as id2 ,'send' as sign from FriendRequest)
select count(*) / (select count(distinct sender_id, send_to_id) from FriendRequest) as rate from temp as t1
                                                                                                   inner join temp as t2 on t1.id1 = t2.id1
  and t1.id2 = t2.id2
  and t1.sign <> t2.sign
  and t1.sign = 'accept';


-- 好友申请II
Create table If Not Exists RequestAccepted (requester_id int not null, accepter_id int null, accept_date date null);

insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('1', '2', '2016/06/03');
insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('1', '3', '2016/06/08');
insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('2', '3', '2016/06/08');
insert into RequestAccepted (requester_id, accepter_id, accept_date) values ('3', '4', '2016/06/09');

drop table RequestAccepted;

select
  r.id1,
  count(*) as count
from (
  select requester_id as id1, accepter_id as id2 from RequestAccepted
  union all
  select accepter_id as id1, requester_id as id2 from RequestAccepted) as r
group by r.id1
order by count desc;

-- 寻找今年具有正收入的用户
select customer_id from Customers where year = 2021 and revenue > 0;

-- 从不购买的客户
select name as Customers
from Customers
where id not in (select customerId from Orders where 1 = 1);
select name as Customers
from Customers as c
where not exists(select 1 from Orders as o where o.customerId = c.id);

-- 计算特殊奖金
select employee_id,
       (case when (employee_id % 2 <> 0 and substr(name, 1, 1) <> 'M') then salary else 0 end) as bonus
from employees order by employee_id;

-- 购买了A B却没购买C的用户
Create table If Not Exists Customers (customer_id int, customer_name varchar(30));
Create table If Not Exists Orders (order_id int, customer_id int, product_name varchar(30));
Truncate table Customers;
insert into Customers (customer_id, customer_name) values ('1', 'Daniel');
insert into Customers (customer_id, customer_name) values ('2', 'Diana');
insert into Customers (customer_id, customer_name) values ('3', 'Elizabeth');
insert into Customers (customer_id, customer_name) values ('4', 'Jhon');
Truncate table Orders;
insert into Orders (order_id, customer_id, product_name) values ('10', '1', 'A');
insert into Orders (order_id, customer_id, product_name) values ('20', '1', 'B');
insert into Orders (order_id, customer_id, product_name) values ('30', '1', 'D');
insert into Orders (order_id, customer_id, product_name) values ('40', '1', 'C');
insert into Orders (order_id, customer_id, product_name) values ('50', '2', 'A');
insert into Orders (order_id, customer_id, product_name) values ('60', '3', 'A');
insert into Orders (order_id, customer_id, product_name) values ('70', '3', 'B');
insert into Orders (order_id, customer_id, product_name) values ('80', '3', 'D');
insert into Orders (order_id, customer_id, product_name) values ('90', '4', 'C');

-- 笨办法 开始没想到 通过单列过滤再去整体过滤
select c.customer_id, c.customer_name
from Customers as c
       inner join(select distinct customer_id
                  from Orders
                  where customer_id in (select t.A as customer_id
                                        from (select (
                                                       case
                                                         when product_name = 'A' then customer_id
                                                         else 0 end
                                                       ) as A
                                              from Orders) as t
                                        where t.A != 0) and customer_id in (select z.B from (select (
                                 case
                                     when product_name = 'B' then customer_id
                                     else 0 end
                                 ) as B
                      from Orders) as z where z.B != 0)
  and customer_id not in (select y.C from (select (
                                     case
                                         when product_name = 'C' then customer_id
                                         else 0 end
                                     ) as C
                          from Orders) as y where y.C != 0) ) as e
where e.customer_id = c.customer_id;

-- 通过计数去过滤
select o.customer_id, c.customer_name
from orders o
       left join customers c
                 on o.customer_id = c.customer_id
group by customer_id
having SUM(if(product_name = 'A', 1, 0)) > 0
   and SUM(if(product_name = 'B', 1, 0)) > 0
   and SUM(if(product_name = 'C', 1, 0)) = 0;

-- 未在2020卖出的卖家
select seller_name from (
                          select max(o.sale_date) as ma, seller_name
                          from Orders as o right join seller as s on o.seller_id = s.seller_id group by seller_name) as t
where t.ma < '2020-01-01' or t.ma > '2020-12-31' or t.ma is null order by seller_name;

-- 每位学生的最高成绩
Create table If Not Exists Enrollments (student_id int, course_id int, grade int);
Truncate table Enrollments;
insert into Enrollments (student_id, course_id, grade) values ('2', '2', '95');
insert into Enrollments (student_id, course_id, grade) values ('2', '3', '95');
insert into Enrollments (student_id, course_id, grade) values ('1', '1', '90');
insert into Enrollments (student_id, course_id, grade) values ('1', '2', '99');
insert into Enrollments (student_id, course_id, grade) values ('3', '1', '80');
insert into Enrollments (student_id, course_id, grade) values ('3', '2', '75');
insert into Enrollments (student_id, course_id, grade) values ('3', '3', '82');

drop table Enrollments;

select student_id, course_id, grade
from (select t.*,
             rank() over (partition by student_id order by course_id) as y
      from (select e.*,
                   rank() over (partition by student_id order by grade desc) as ra
            from Enrollments as e) as t
      where t.ra = 1) as e
where e.y = 1
order by e.student_id;

drop table Users;
drop table Rides;
-- 排名靠前的旅行者
select u.name, if(t.su is null, 0, t.su) as travelled_distance
from Users as u
       left join (select user_id, sum(distance) as su
                  from Rides
                  group by user_id) as t on t.user_id = u.id
order by travelled_distance desc, name;

-- 销售员
Create table If Not Exists SalesPerson (sales_id int, name varchar(255), salary int, commission_rate int, hire_date date);
Create table If Not Exists Company (com_id int, name varchar(255), city varchar(255));
Create table If Not Exists Orders (order_id int, order_date date, com_id int, sales_id int, amount int);
Truncate table SalesPerson;
insert into SalesPerson (sales_id, name, salary, commission_rate, hire_date) values ('1', 'John', '100000', '6', '4/1/2006');
insert into SalesPerson (sales_id, name, salary, commission_rate, hire_date) values ('2', 'Amy', '12000', '5', '5/1/2010');
insert into SalesPerson (sales_id, name, salary, commission_rate, hire_date) values ('3', 'Mark', '65000', '12', '12/25/2008');
insert into SalesPerson (sales_id, name, salary, commission_rate, hire_date) values ('4', 'Pam', '25000', '25', '1/1/2005');
insert into SalesPerson (sales_id, name, salary, commission_rate, hire_date) values ('5', 'Alex', '5000', '10', '2/3/2007');
  Truncate table Company;
insert into Company (com_id, name, city) values ('1', 'RED', 'Boston');
insert into Company (com_id, name, city) values ('2', 'ORANGE', 'New York');
insert into Company (com_id, name, city) values ('3', 'YELLOW', 'Boston');
insert into Company (com_id, name, city) values ('4', 'GREEN', 'Austin');
  Truncate table Orders;
insert into Orders (order_id, order_date, com_id, sales_id, amount) values ('1', '1/1/2014', '3', '4', '10000');
insert into Orders (order_id, order_date, com_id, sales_id, amount) values ('2', '2/1/2014', '4', '5', '5000');
insert into Orders (order_id, order_date, com_id, sales_id, amount) values ('3', '3/1/2014', '1', '1', '50000');
insert into Orders (order_id, order_date, com_id, sales_id, amount) values ('4', '4/1/2014', '1', '4', '25000');

drop table Orders;
drop table Company;
drop table SalesPerson;

with temp as (
  select * from Orders where com_id = (select com_id from Company where name = 'RED'))
select name from SalesPerson as s where not exists(select 1 from temp as t where s.sales_id = t.sales_id);

-- 计算布尔表达式的值
Create Table If Not Exists Variables (name varchar(3), value int);
Create Table If Not Exists Expressions (left_operand varchar(3), operator ENUM('>', '<', '='), right_operand varchar(3));
Truncate table Variables;
insert into Variables (name, value) values ('x', '66');
insert into Variables (name, value) values ('y', '77');
Truncate table Expressions;
insert into Expressions (left_operand, operator, right_operand) values ('x', '>', 'y');
insert into Expressions (left_operand, operator, right_operand) values ('x', '<', 'y');
insert into Expressions (left_operand, operator, right_operand) values ('x', '=', 'y');
insert into Expressions (left_operand, operator, right_operand) values ('y', '>', 'x');
insert into Expressions (left_operand, operator, right_operand) values ('y', '<', 'x');
insert into Expressions (left_operand, operator, right_operand) values ('x', '=', 'x');

-- 解法一
select left_operand, operator, right_operand ,(
  case
    when operator = '=' and lef = rig then 'true'
    when operator = '>' and lef > rig then 'true'
    when operator = '<' and lef < rig then 'true'
    else 'false' end
  ) as value
from (
  select left_operand,
  (select value from Variables where left_operand = name) as lef,
  operator,
  right_operand,
  (select value from Variables where right_operand = name) as rig
  from Expressions) as t;

-- 解法二
select
  e.left_operand,
  e.operator,
  e.right_operand,
  case
    when e.operator = '>' then
      case
        when v1.value > v2.value then 'true'
        else 'false'
        end
    when e.operator = '<' then
      case
        when v1.value < v2.value then 'true'
        else 'false'
        end
    when e.operator = '=' then
      case
        when v1.value = v2.value then 'true'
        else 'false'
        end
    end as value
from
  expressions e
  join
  variables v1 on e.left_operand = v1.name
  join
  variables v2 on e.right_operand = v2.name;

-- 仓库经理
Create table If Not Exists Warehouse (name varchar(50), product_id int, units int);

Create table If Not Exists Products (product_id int, product_name varchar(50), Width int,Length int,Height int);
Truncate table Warehouse;
insert into Warehouse (name, product_id, units) values ('LCHouse1', '1', '1');
insert into Warehouse (name, product_id, units) values ('LCHouse1', '2', '10');
insert into Warehouse (name, product_id, units) values ('LCHouse1', '3', '5');
insert into Warehouse (name, product_id, units) values ('LCHouse2', '1', '2');
insert into Warehouse (name, product_id, units) values ('LCHouse2', '2', '2');
insert into Warehouse (name, product_id, units) values ('LCHouse3', '4', '1');
Truncate table Products;
insert into Products (product_id, product_name, Width, Length, Height) values ('1', 'LC-TV', '5', '50', '40');
insert into Products (product_id, product_name, Width, Length, Height) values ('2', 'LC-KeyChain', '5', '5', '5');
insert into Products (product_id, product_name, Width, Length, Height) values ('3', 'LC-Phone', '2', '10', '10');
insert into Products (product_id, product_name, Width, Length, Height) values ('4', 'LC-T-Shirt', '4', '10', '20');

select w.name,
       sum(p.Width * p.Length * p.Height * w.units) as single
from Warehouse as w inner join Products as p where p.product_id = w.product_id group by name;

-- 订单最多的客户
Create table If Not Exists orders (order_number int, customer_number int);
Truncate table orders;
insert into orders (order_number, customer_number) values ('1', '1');
insert into orders (order_number, customer_number) values ('2', '2');
insert into orders (order_number, customer_number) values ('3', '3');
insert into orders (order_number, customer_number) values ('4', '3');
insert into orders (order_number, customer_number) values ('2', '4');
insert into orders (order_number, customer_number) values ('4', '4');

drop table orders;

-- 进阶解法 普通解法这里limit就好
select customer_number
from (select customer_number,
             (select max(con) as max
              from (select count(o.order_number) as con
                    from orders as o
                    group by o.customer_number) as t) - con as si
      from (select count(o.order_number) as con,
                   o.customer_number
            from orders as o
            group by o.customer_number
            order by con desc) as y) as r
where r.si = 0;

-- 查询每个员工花费的总时间
select event_day as day,
       emp_id,
       sum(out_time - in_time) as total_time
from Employees group by emp_id, event_day;

-- 两人之间通话次数
Create table If Not Exists Calls (from_id int, to_id int, duration int);
Truncate table Calls;
insert into Calls (from_id, to_id, duration) values ('1', '2', '59');
insert into Calls (from_id, to_id, duration) values ('2', '1', '11');
insert into Calls (from_id, to_id, duration) values ('1', '3', '20');
insert into Calls (from_id, to_id, duration) values ('3', '4', '100');
insert into Calls (from_id, to_id, duration) values ('3', '4', '200');
insert into Calls (from_id, to_id, duration) values ('3', '4', '200');
insert into Calls (from_id, to_id, duration) values ('4', '3', '499');

with temp as (
  select from_id, to_id, duration from Calls
  union all
  select to_id as from_id, from_id as to_id, duration from Calls)
select from_id as person1, to_id as person2, sum(duration) as total_duration, count(from_id) as call_count from temp group by from_id, to_id having from_id < to_id;

-- 存款
Create table If Not Exists Users (account int, name varchar(20));
Create table If Not Exists Transactions (trans_id int, account int, amount int, transacted_on date);
Truncate table Users;
insert into Users (account, name) values ('900001', 'Alice');
insert into Users (account, name) values ('900002', 'Bob');
insert into Users (account, name) values ('900003', 'Charlie');
Truncate table Transactions;
insert into Transactions (trans_id, account, amount, transacted_on) values ('1', '900001', '7000', '2020-08-01');
insert into Transactions (trans_id, account, amount, transacted_on) values ('2', '900001', '7000', '2020-09-01');
insert into Transactions (trans_id, account, amount, transacted_on) values ('3', '900001', '-3000', '2020-09-02');
insert into Transactions (trans_id, account, amount, transacted_on) values ('4', '900002', '1000', '2020-09-12');
insert into Transactions (trans_id, account, amount, transacted_on) values ('5', '900003', '6000', '2020-08-07');
insert into Transactions (trans_id, account, amount, transacted_on) values ('6', '900003', '6000', '2020-09-07');
insert into Transactions (trans_id, account, amount, transacted_on) values ('7', '900003', '-4000', '2020-09-11');
drop table Transactions;
drop table Users;
select U.name,
       sum(t.amount) as balance
from Transactions as t
       inner join Users U on t.account = U.account
group by t.account
having balance >= 10000;

-- 2020年6月7月都消费超过100的人
Create table If Not Exists Customers (customer_id int, name varchar(30), country varchar(30));
Create table If Not Exists Product (product_id int, description varchar(30), price int);

Create table If Not Exists Orders (order_id int, customer_id int, product_id int, order_date date, quantity int);

Truncate table Customers;
insert into Customers (customer_id, name, country) values ('1', 'Winston', 'USA');
insert into Customers (customer_id, name, country) values ('2', 'Jonathan', 'Peru');
insert into Customers (customer_id, name, country) values ('3', 'Moustafa', 'Egypt');
Truncate table Product;
insert into Product (product_id, description, price) values ('10', 'LC Phone', '300');
insert into Product (product_id, description, price) values ('20', 'LC T-Shirt', '10');
insert into Product (product_id, description, price) values ('30', 'LC Book', '45');
insert into Product (product_id, description, price) values ('40', 'LC Keychain', '2');
Truncate table Orders;
insert into Orders (order_id, customer_id, product_id, order_date, quantity) values ('1', '1', '10', '2020-06-10', '1');
insert into Orders (order_id, customer_id, product_id, order_date, quantity) values ('2', '1', '20', '2020-07-01', '1');
insert into Orders (order_id, customer_id, product_id, order_date, quantity) values ('3', '1', '30', '2020-07-08', '2');
insert into Orders (order_id, customer_id, product_id, order_date, quantity) values ('4', '2', '10', '2020-06-15', '2');
insert into Orders (order_id, customer_id, product_id, order_date, quantity) values ('5', '2', '40', '2020-07-01', '10');
insert into Orders (order_id, customer_id, product_id, order_date, quantity) values ('6', '3', '20', '2020-06-24', '2');
insert into Orders (order_id, customer_id, product_id, order_date, quantity) values ('7', '3', '30', '2020-06-25', '2');
insert into Orders (order_id, customer_id, product_id, order_date, quantity) values ('9', '3', '30', '2020-05-08', '3');
drop table Orders, Product, Customers;

select a.customer_id,
       a.name
from (
       select u.customer_id,
              count(c.name) as num,
              c.name
       from(
             select year(o.order_date) as year,
                    month(o.order_date) as month,
                    sum(o.quantity * p.price) as sum,
                    customer_id
             from Orders as o
                    inner join Product as p
                               on o.product_id = p.product_id
             group by customer_id, month
             having sum >= 100 and month in ('6', '7') and year = '2020') as u
             inner join Customers as c
                        on c.customer_id = u.customer_id group by c.name having num = 2) as a;

-- 适宜播放的儿童电影
Create table If Not Exists TVProgram (program_date date, content_id int, channel varchar(30));
Create table If Not Exists Content (content_id varchar(30), title varchar(30), Kids_content ENUM('Y', 'N'), content_type varchar(30));
Truncate table TVProgram;
insert into TVProgram (program_date, content_id, channel) values ('2020-06-10 08:00', '1', 'LC-Channel');
insert into TVProgram (program_date, content_id, channel) values ('2020-05-11 12:00', '2', 'LC-Channel');
insert into TVProgram (program_date, content_id, channel) values ('2020-05-12 12:00', '3', 'LC-Channel');
insert into TVProgram (program_date, content_id, channel) values ('2020-05-13 14:00', '4', 'Disney Ch');
insert into TVProgram (program_date, content_id, channel) values ('2020-06-18 14:00', '4', 'Disney Ch');
insert into TVProgram (program_date, content_id, channel) values ('2020-07-15 16:00', '5', 'Disney Ch');
Truncate table Content;
insert into Content (content_id, title, Kids_content, content_type) values ('1', 'Leetcode Movie', 'N', 'Movies');
insert into Content (content_id, title, Kids_content, content_type) values ('2', 'Alg. for Kids', 'Y', 'Series');
insert into Content (content_id, title, Kids_content, content_type) values ('3', 'Database Sols', 'N', 'Series');
insert into Content (content_id, title, Kids_content, content_type) values ('4', 'Aladdin', 'Y', 'Movies');
insert into Content (content_id, title, Kids_content, content_type) values ('5', 'Cinderella', 'Y', 'Movies');
drop table Content;
drop table TVProgram;
select distinct c.title
from Content as c inner join TVProgram as t
                             on t.program_date like '2020-06%'
                               and t.content_id = c.content_id
                               and c.Kids_content = 'Y'
                               and c.content_type = 'Movies';

-- 唯一领导和合伙人
Create table If Not Exists DailySales(date_id date, make_name varchar(20), lead_id int, partner_id int);
Truncate table DailySales;
insert into DailySales (date_id, make_name, lead_id, partner_id) values ('2020-12-8', 'toyota', '0', '1');
insert into DailySales (date_id, make_name, lead_id, partner_id) values ('2020-12-8', 'toyota', '1', '0');
insert into DailySales (date_id, make_name, lead_id, partner_id) values ('2020-12-8', 'toyota', '1', '2');
insert into DailySales (date_id, make_name, lead_id, partner_id) values ('2020-12-7', 'toyota', '0', '2');
insert into DailySales (date_id, make_name, lead_id, partner_id) values ('2020-12-7', 'toyota', '0', '1');
insert into DailySales (date_id, make_name, lead_id, partner_id) values ('2020-12-8', 'honda', '1', '2');
insert into DailySales (date_id, make_name, lead_id, partner_id) values ('2020-12-8', 'honda', '2', '1');
insert into DailySales (date_id, make_name, lead_id, partner_id) values ('2020-12-7', 'honda', '0', '1');
insert into DailySales (date_id, make_name, lead_id, partner_id) values ('2020-12-7', 'honda', '1', '2');
insert into DailySales (date_id, make_name, lead_id, partner_id) values ('2020-12-7', 'honda', '2', '1');

drop table DailySales;

select f.date_id, f.make_name,
       max(o) as unique_leads,
       max(y) as unique_partners
from (
       select d.*,
              dense_rank() over (partition by date_id, make_name order by lead_id) as o,
              dense_rank() over (partition by date_id, make_name order by partner_id) as y
       from DailySales as d) as f group by date_id, make_name;

-- 组合表 easy
Create table If Not Exists Person (personId int, firstName varchar(255), lastName varchar(255));
Create table If Not Exists Address (addressId int, personId int, city varchar(255), state varchar(255));
Truncate table Person;
insert into Person (personId, lastName, firstName) values ('1', 'Wang', 'Allen');
insert into Person (personId, lastName, firstName) values ('2', 'Alice', 'Bob');
Truncate table Address;
insert into Address (addressId, personId, city, state) values (1, '2', 'New York City', 'New York');
insert into Address (addressId, personId, city, state) values (2, '3', 'Leetcode', 'California');
drop table Address;
drop table Person;
select firstName, lastName, a.city, a.state from Person as p left join Address as a on p.personId = a.personId;

Create table If Not Exists LogInfo (account_id int, ip_address int, login datetime, logout datetime);
Truncate table LogInfo;
insert into LogInfo (account_id, ip_address, login, logout) values ('1', '1', '2021-02-01 09:00:00', '2021-02-01 09:30:00');
insert into LogInfo (account_id, ip_address, login, logout) values ('1', '2', '2021-02-01 08:00:00', '2021-02-01 11:30:00');
insert into LogInfo (account_id, ip_address, login, logout) values ('2', '6', '2021-02-01 20:30:00', '2021-02-01 22:00:00');
insert into LogInfo (account_id, ip_address, login, logout) values ('2', '7', '2021-02-02 20:30:00', '2021-02-02 22:00:00');
insert into LogInfo (account_id, ip_address, login, logout) values ('3', '9', '2021-02-01 16:00:00', '2021-02-01 16:59:59');
insert into LogInfo (account_id, ip_address, login, logout) values ('3', '13', '2021-02-01 17:00:00', '2021-02-01 17:59:59');
insert into LogInfo (account_id, ip_address, login, logout) values ('4', '10', '2021-02-01 16:00:00', '2021-02-01 17:00:00');
insert into LogInfo (account_id, ip_address, login, logout) values ('4', '11', '2021-02-01 17:00:00', '2021-02-01 17:59:59');

drop table LogInfo;
select distinct l1.account_id
from LogInfo as l1
       inner join loginfo as l2
                  on l1.account_id = l2.account_id
                    and l1.ip_address <> l2.ip_address
                    and ((l2.logout >= l1.logout and l2.login <= l1.login) or (l2.login <= l1.logout and l2.logout >= l1.login));

-- 连续座位
Create table If Not Exists Cinema (seat_id int primary key auto_increment, free bool);
Truncate table Cinema;
insert into Cinema (seat_id, free) values ('1', '1');
insert into Cinema (seat_id, free) values ('2', '1');
insert into Cinema (seat_id, free) values ('3', '1');
insert into Cinema (seat_id, free) values ('4', '1');
insert into Cinema (seat_id, free) values ('5', '1');
insert into Cinema (seat_id, free) values ('6', '0');
insert into Cinema (seat_id, free) values ('7', '1');

drop table Cinema;

with temp as (select c.*,
                     c.seat_id - row_number() over () as ti
              from (select * from Cinema where free = 1) as c)
select seat_id from temp as t where t.ti in (select y.ti from (select count(ti) as con, ti from temp group by ti having con > 1) as y);

-- 2020年最后一次登录日期
select user_id, time_stamp
from (select l.*,
             rank() over (partition by user_id order by time_stamp desc) as ra
      from Logins as l
      where time_stamp like '2020-%') as t
where ra = 1;

-- 578查询最高回答率的问题 没拿下
Create table If Not Exists SurveyLog (id int, action varchar(255), question_id int, answer_id int, q_num int, timestamp int);
Truncate table SurveyLog;
insert into SurveyLog (id, action, question_id, answer_id, q_num, timestamp) values ('5', 'show', '285', null, '1', '123');
insert into SurveyLog (id, action, question_id, answer_id, q_num, timestamp) values ('5', 'answer', '285', '124124', '1', '124');
insert into SurveyLog (id, action, question_id, answer_id, q_num, timestamp) values ('5', 'show', '285', '124154', '1', '126');
insert into SurveyLog (id, action, question_id, answer_id, q_num, timestamp) values ('5', 'show', '369', null, '2', '125');
insert into SurveyLog (id, action, question_id, answer_id, q_num, timestamp) values ('5', 'skip', '369', null, '2', '126');

select question_id
from SurveyLog
group by question_id
order by sum(action = 'answer') / sum(action = 'show') desc,
         question_id limit 1;

select question_id,
       case
         when count(case when action = 'show' then id else null end) = 0 then 0
         else count(case when action = 'answer' then id else null end) / count(case when action = 'show' then id else null end)
         end as rate
from SurveyLog
group by question_id;

-- 部门最高工资
select Department, Employee, Salary
from (select d.name                                                           as Department,
             e.name                                                           as Employee,
             e.salary                                                         as Salary,
             rank() over (partition by e.departmentId order by e.salary desc) as rn
      from Employee as e
             left join Department as d
                       on e.departmentId = d.id) as r
where r.rn = 1;


Create table If Not Exists Scores (id int, score DECIMAL(3,2));

insert into Scores (id, score) values ('1', '3.5');
insert into Scores (id, score) values ('2', '3.65');
insert into Scores (id, score) values ('3', '4.0');
insert into Scores (id, score) values ('4', '3.85');
insert into Scores (id, score) values ('5', '4.0');
insert into Scores (id, score) values ('6', '3.65');
drop table Scores;
select score, rank() over(order by score desc) as `rank` from Scores order by score;

select
  score,
  dense_rank() over(order by score desc) as `rank`
from Scores a
order by score desc;

-- 删除重复邮箱
Create table If Not Exists Person (Id int, Email varchar(255));
Truncate table Person;
insert into Person (id, email) values ('1', 'john@example.com');
insert into Person (id, email) values ('2', 'bob@example.com');
insert into Person (id, email) values ('3', 'john@example.com');

drop table Person;

delete
from Person
where id in (select t.Id
             from (select p.*, rank() over (partition by p.email order by p.Id) as rn from Person as p) as t
             where t.rn <> 1);

-- 比前一天高的温度
Create table If Not Exists Weather (id int, recordDate date, temperature int);
Truncate table Weather;
insert into Weather (id, recordDate, temperature) values ('1', '2015-01-01', '10');
insert into Weather (id, recordDate, temperature) values ('2', '2015-01-02', '25');
insert into Weather (id, recordDate, temperature) values ('3', '2015-01-03', '20');
insert into Weather (id, recordDate, temperature) values ('4', '2015-01-04', '30');

select id from (
                 select w1.temperature as beT,
                        w2.temperature as afT,
                        w2.id
                 from Weather as w1
                        inner join Weather as w2
                          on w1.recordDate = date_add(w2.recordDate, INTERVAL -1 DAY)) as t where afT > beT;

-- 查询员工的累计薪水
Create table If Not Exists Employee (id int, month int, salary int);
Truncate table Employee;
insert into Employee (id, month, salary) values ('1', '1', '20');
insert into Employee (id, month, salary) values ('2', '1', '20');
insert into Employee (id, month, salary) values ('1', '2', '30');
insert into Employee (id, month, salary) values ('2', '2', '30');
insert into Employee (id, month, salary) values ('3', '2', '40');
insert into Employee (id, month, salary) values ('1', '3', '40');
insert into Employee (id, month, salary) values ('3', '3', '60');
insert into Employee (id, month, salary) values ('1', '4', '60');
insert into Employee (id, month, salary) values ('3', '4', '70');
insert into Employee (id, month, salary) values ('1', '7', '90');
insert into Employee (id, month, salary) values ('1', '8', '90');

drop table Employee;

select e1.id,e1.month,
       sum(e2.salary) as salary
from Employee as e1
       inner join Employee as e2
                  on e1.id = e2.id
                    and e1.month >= e2.month
                    and e1.month < e2.month + 3
                    and (e1.id, e1.month) not in (select id, max(month) from employee group by id)
group by e1.id, e1.month
order by e1.id, e1.month desc;

select e.id, e.month,
       sum(salary) over (partition by id order by e.month range 2 preceding) as Salary
from Employee as e where (e.id, e.month) not in (select id, max(month) from employee group by id) order by e.id, e.month desc;

-- 员工薪水中位数
Create table If Not Exists Employee (id int, company varchar(255), salary int);
Truncate table Employee;
insert into Employee (id, company, salary) values ('1', 'A', '2341');
insert into Employee (id, company, salary) values ('2', 'A', '341');
insert into Employee (id, company, salary) values ('3', 'A', '15');
insert into Employee (id, company, salary) values ('4', 'A', '15314');
insert into Employee (id, company, salary) values ('5', 'A', '451');
insert into Employee (id, company, salary) values ('6', 'A', '513');
insert into Employee (id, company, salary) values ('7', 'B', '15');
insert into Employee (id, company, salary) values ('8', 'B', '13');
insert into Employee (id, company, salary) values ('9', 'B', '1154');
insert into Employee (id, company, salary) values ('10', 'B', '1345');
insert into Employee (id, company, salary) values ('11', 'B', '1221');
insert into Employee (id, company, salary) values ('12', 'B', '234');
insert into Employee (id, company, salary) values ('13', 'C', '2345');
insert into Employee (id, company, salary) values ('14', 'C', '2645');
insert into Employee (id, company, salary) values ('15', 'C', '2645');
insert into Employee (id, company, salary) values ('16', 'C', '2652');
insert into Employee (id, company, salary) values ('17', 'C', '65');

select id, company, salary
from (
       select e.*,
              row_number() over (partition by e.company order by salary, id) as ase,
              row_number() over (partition by e.company order by salary desc, id desc) as dase,
              count(id) over (partition by e.company) as total
       from Employee as e) as t1
where t1.ase >= t1.total / 2 and t1.dase >= t1.total / 2;

-- 平面上最近的距离
Create Table If Not Exists Point2D (x int not null, y int not null);
Truncate table Point2D;
insert into Point2D (x, y) values ('-1', '-1');
insert into Point2D (x, y) values ('0', '0');
insert into Point2D (x, y) values ('-1', '-2');

drop table Point2D;

select min(round(sqrt(abs(p1.x - p2.x) * abs(p1.x - p2.x) + abs(p1.y - p2.y) * abs(p1.y - p2.y)), 2)) as shortest
from Point2D as p1 inner join Point2D as p2 on p1.x <> p2.x or p1.y <> p2.y;

-- 统计体育馆的人流量
Create table If Not Exists Stadium (id int, visit_date DATE NULL, people int);
Truncate table Stadium;
insert into Stadium (id, visit_date, people) values ('1', '2017-01-01', '10');
insert into Stadium (id, visit_date, people) values ('2', '2017-01-02', '109');
insert into Stadium (id, visit_date, people) values ('3', '2017-01-03', '150');
insert into Stadium (id, visit_date, people) values ('4', '2017-01-04', '99');
insert into Stadium (id, visit_date, people) values ('5', '2017-01-05', '145');
insert into Stadium (id, visit_date, people) values ('6', '2017-01-06', '1455');
insert into Stadium (id, visit_date, people) values ('7', '2017-01-07', '199');
insert into Stadium (id, visit_date, people) values ('8', '2017-01-09', '188');

drop table Stadium;

select d.id, d.visit_date, d.people
from (select s.*,
             count(1) over (partition by s.rn) as ct
      from (select t.*,
                   id - row_number() over (order by id ) as rn
            from Stadium as t
            where people >= 100) as s) as d
where ct >= 3
order by d.visit_date;

-- 锦标赛优胜者
Create table If Not Exists Players (player_id int, group_id int);
Create table If Not Exists Matches (match_id int, first_player int, second_player int, first_score int, second_score int);
Truncate table Players;
insert into Players (player_id, group_id) values ('10', '2');
insert into Players (player_id, group_id) values ('15', '1');
insert into Players (player_id, group_id) values ('20', '3');
insert into Players (player_id, group_id) values ('25', '1');
insert into Players (player_id, group_id) values ('30', '1');
insert into Players (player_id, group_id) values ('35', '2');
insert into Players (player_id, group_id) values ('40', '3');
insert into Players (player_id, group_id) values ('45', '1');
insert into Players (player_id, group_id) values ('50', '2');
Truncate table Matches;
insert into Matches (match_id, first_player, second_player, first_score, second_score) values ('1', '15', '45', '3', '0');
insert into Matches (match_id, first_player, second_player, first_score, second_score) values ('2', '30', '25', '1', '2');
insert into Matches (match_id, first_player, second_player, first_score, second_score) values ('3', '30', '15', '2', '0');
insert into Matches (match_id, first_player, second_player, first_score, second_score) values ('4', '40', '20', '5', '2');
insert into Matches (match_id, first_player, second_player, first_score, second_score) values ('5', '35', '50', '1', '1');

drop table Matches;
drop table Players;

with temp as (select m.first_player as fir,
                     m.first_score  as fc
              from Matches as m
              union all
              select m.second_player as fir,
                     m.second_score  as fc
              from Matches as m)
select group_id, fir as player_id
from (select t.*,
             row_number() over (partition by group_id order by num desc, fir) as rnk
      from (select fir, sum(fc) as num, group_id
            from temp
                   inner join Players on player_id = fir
            group by fir) as t) as r
where r.rnk = 1;


-- 每次访问的交易次数
Create table If Not Exists Visits (user_id int, visit_date date);
Create table If Not Exists Transactions (user_id int, transaction_date date, amount int);
Truncate table Visits;
insert into Visits (user_id, visit_date) values ('1', '2020-01-01');
insert into Visits (user_id, visit_date) values ('2', '2020-01-02');
insert into Visits (user_id, visit_date) values ('12', '2020-01-01');
insert into Visits (user_id, visit_date) values ('19', '2020-01-03');
insert into Visits (user_id, visit_date) values ('1', '2020-01-02');
insert into Visits (user_id, visit_date) values ('2', '2020-01-03');
insert into Visits (user_id, visit_date) values ('1', '2020-01-04');
insert into Visits (user_id, visit_date) values ('7', '2020-01-11');
insert into Visits (user_id, visit_date) values ('9', '2020-01-25');
insert into Visits (user_id, visit_date) values ('8', '2020-01-28');
Truncate table Transactions;
insert into Transactions (user_id, transaction_date, amount) values ('1', '2020-01-02', '120');
insert into Transactions (user_id, transaction_date, amount) values ('2', '2020-01-03', '22');
insert into Transactions (user_id, transaction_date, amount) values ('7', '2020-01-11', '232');
insert into Transactions (user_id, transaction_date, amount) values ('1', '2020-01-04', '7');
insert into Transactions (user_id, transaction_date, amount) values ('9', '2020-01-25', '33');
insert into Transactions (user_id, transaction_date, amount) values ('9', '2020-01-25', '66');
insert into Transactions (user_id, transaction_date, amount) values ('8', '2020-01-28', '1');
insert into Transactions (user_id, transaction_date, amount) values ('9', '2020-01-25', '99');

with temp as (
  select user_id, transaction_date, amount from Transactions
  union all
  select user_id, visit_date as transaction_date, 0 from Visits) select t.*, count(user_id) from temp as t group by user_id, transaction_date;



Create table If Not Exists Experiments (experiment_id int, platform ENUM('Android', 'IOS', 'Web'), experiment_name ENUM('Reading', 'Sports', 'Programming'));
Truncate table Experiments;
insert into Experiments (experiment_id, platform, experiment_name) values ('4', 'IOS', 'Programming');
insert into Experiments (experiment_id, platform, experiment_name) values ('13', 'IOS', 'Sports');
insert into Experiments (experiment_id, platform, experiment_name) values ('14', 'Android', 'Reading');
insert into Experiments (experiment_id, platform, experiment_name) values ('8', 'Web', 'Reading');
insert into Experiments (experiment_id, platform, experiment_name) values ('12', 'Web', 'Reading');
insert into Experiments (experiment_id, platform, experiment_name) values ('18', 'Web', 'Programming');


Create table If Not Exists Department (id int, revenue int, month varchar(5));
Truncate table Department;
insert into Department (id, revenue, month) values ('1', '8000', 'Jan');
insert into Department (id, revenue, month) values ('2', '9000', 'Jan');
insert into Department (id, revenue, month) values ('3', '10000', 'Feb');
insert into Department (id, revenue, month) values ('1', '7000', 'Feb');
insert into Department (id, revenue, month) values ('1', '6000', 'Mar');

drop table Department;

select d.id,
       sum(case when d.month = 'Jan' then d.revenue else null end) as Jan,
       sum(case when d.month = 'Feb' then d.revenue else null end) as Feb,
       sum(case when d.month = 'Mar' then d.revenue else null end) as Mar
from Department as d group by id;
