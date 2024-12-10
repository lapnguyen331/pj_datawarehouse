Đây là kết quả làm việc nhóm của đội phát triển tool báo cáo giá môn học datawarehouse dưới sự chỉ dẫn của thầy Nguyễn Đức Công Song , trường đại học Nông Lâm

Repo gồm source 4 module, 1 proccess manager, tài liệu dự án + script sql khởi tạo database, và 1 số ảnh thiết kế tham khảo


Mục tiêu của đồ án:

  -tạo ra 1 tool cho phép client xem các báo cáo về giá xe máy theo tháng, ngày, brand, trend tăng giảm giá
  
  
Ý nghĩa của các tool:

  -module 1.1 :sử dụng selenium crawl data từ trang lazada.com mục xe máy
  
  -module 1.2 :sử dụng selenium crawl data từ trang hoangcau.com
  
  -module 2 : load data từ csv vào dbstaging_temp
  
  -module3 : transform, validate, aggreegation dữ liệu từ staging vào warehouse
  
  -module4: từ warehouse lấy ra dữ liệu cần để đổ vào datamart

  
Cách chạy tool:

  tool là 1 phiên bản chưa thực sự hoàn thiện nhất là lập lịch, quản lí proccess, và cấu hình dbconfig để toàn bộ hệ thống dễ dàng scale

  
  *chạy từng module:
  
    -module 1.1, 1.2 , 2 viết bằng python, bạn cần cài toàn bộ dependency trong file requirement.txt
    
    -module 3,4 viết bằng java jdk 17.0.1.2, bạn cần cài đúng phiên bản để có thể khởi chạy các file jar trong proccess manager
    
  *chạy tools:
  
    -mở proccess manager và tiến hành cài các requirement là có thể chạy, không cần mở các module vì tôi đã tích hợp các module vào trong

    
Các lỗi có thể xảy ra:

  -nguồn dữ liệu lazada.com và hoangcau.com không còn tồn tại trên url
  
  -xung đột các gói cài đặt
  
  -nguồn dữ liệu sử dụng service chặn crawler
  
Lời nói cuối:


  -đây là sản phẩm thực sự chưa hoàn thiện, chúng tôi đã bị cháy deadline do khả năng quản lí rủi ro yếu kém cũng như kinh nghiệm làm việc non nớt
  
  -môn warehouse thực sự rất nhiều thứ hay ho nên nếu bạn đang học thì hãy học thật tốt nhé 


  
  ....
  
  Nông Lâm 12/10/2024 
  
  ...

