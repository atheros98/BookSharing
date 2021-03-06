USE [BookShareProject]
GO
/****** Object:  Table [dbo].[User]    Script Date: 07/27/2018 08:01:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fullName] [nvarchar](50) NOT NULL,
	[birthday] [date] NOT NULL,
	[avatar] [nvarchar](200) NULL,
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[address] [nvarchar](500) NULL,
	[phoneNumber] [nvarchar](15) NULL,
	[linkFacebook] [nvarchar](50) NULL,
	[userPoint] [int] NULL,
	[createDate] [date] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[User] ON
INSERT [dbo].[User] ([id], [fullName], [birthday], [avatar], [username], [password], [email], [address], [phoneNumber], [linkFacebook], [userPoint], [createDate]) VALUES (1, N'Nguyen Nhu Thuong', CAST(0x5B200B00 AS Date), N'default.jpg', N'admin', N'admin', N'jmthuong97@gmail.com', N'D403', N'0973038357', N'fb.com', 10, CAST(0x0A3F0B00 AS Date))
INSERT [dbo].[User] ([id], [fullName], [birthday], [avatar], [username], [password], [email], [address], [phoneNumber], [linkFacebook], [userPoint], [createDate]) VALUES (3, N'Nguyen Thi Kim Chi', CAST(0xD0230B00 AS Date), N'nguyenchi1.jpg', N'nguyenchi1', N'Anhthuong1', N'chintkha@gmail.com', NULL, NULL, NULL, 10, CAST(0x763E0B00 AS Date))
INSERT [dbo].[User] ([id], [fullName], [birthday], [avatar], [username], [password], [email], [address], [phoneNumber], [linkFacebook], [userPoint], [createDate]) VALUES (4, N'Nguyễn Vũ Minh', CAST(0x41210B00 AS Date), N'atheros.jpg', N'atheros', N'123456', N'nguyenvuminh98@gmail.com', NULL, NULL, NULL, 10, CAST(0x813E0B00 AS Date))
INSERT [dbo].[User] ([id], [fullName], [birthday], [avatar], [username], [password], [email], [address], [phoneNumber], [linkFacebook], [userPoint], [createDate]) VALUES (5, N'Phan Nguyễn Huy Hiếu', CAST(0xB5210B00 AS Date), N'default.jpg', N'huyhieu', N'123456', N'hieupnh12@gmail.com', NULL, NULL, NULL, 10, CAST(0x833E0B00 AS Date))
SET IDENTITY_INSERT [dbo].[User] OFF
/****** Object:  Table [dbo].[Book]    Script Date: 07/27/2018 08:01:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Book](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](50) NOT NULL,
	[author] [nvarchar](50) NOT NULL,
	[ISBN] [nvarchar](20) NOT NULL,
	[language] [nvarchar](50) NOT NULL,
	[description] [nvarchar](max) NOT NULL,
	[tag] [nvarchar](50) NULL,
	[status] [bit] NULL,
	[idUser] [int] NOT NULL,
 CONSTRAINT [PK_Book] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Book] ON
INSERT [dbo].[Book] ([id], [title], [author], [ISBN], [language], [description], [tag], [status], [idUser]) VALUES (3, N'How to Win Friends and Influence People', N'Dale Carnegie', N'561107', N'Vietnamese', N'Dale Carnegie''''s "How to Win Friends and Influence People" is one of the best-selling, most influential books of all time. The work has been translated into almost every language in the world and is available in hundreds of countries. This is the only self-help book that has consistently topped the New York Times best-selling Books list for 10 years. The English version of the book has sold more than 15 million copies worldwide. The work has a tremendous spread - no matter where you go, any country can see it. The book is considered to be the first and best book, influencing the lives of millions of people around the world.', N'Self-help Book', 1, 3)
INSERT [dbo].[Book] ([id], [title], [author], [ISBN], [language], [description], [tag], [status], [idUser]) VALUES (4, N'Nhà giả kim', N'Paulo Coelho', N'8935235213746', N'Vietnamese', N'Tất cả những trải nghiệm trong chuyến phiêu du theo đuổi vận mệnh của mình đã giúp Santiago thấu hiểu được ý nghĩa sâu xa nhất của hạnh phúc, hòa hợp với vũ trụ và con người.

Tiểu thuyết Nhà giả kim của Paulo Coelho như một câu chuyện cổ tích giản dị, nhân ái, giàu chất thơ, thấm đẫm những minh triết huyền bí của phương Đông. Trong lần xuất bản đầu tiên tại Brazil vào năm 1988, sách chỉ bán được 900 bản. Nhưng, với số phận đặc biệt của cuốn sách dành cho toàn nhân loại, vượt ra ngoài biên giới quốc gia, Nhà giả kim đã làm rung động hàng triệu tâm hồn, trở thành một trong những cuốn sách bán chạy nhất mọi thời đại, và có thể làm thay đổi cuộc đời người đọc.

“Nhưng nhà luyện kim đan không quan tâm mấy đến những điều ấy. Ông đã từng thấy nhiều người đến rồi đi, trong khi ốc đảo và sa mạc vẫn là ốc đảo và sa mạc. Ông đã thấy vua chúa và kẻ ăn xin đi qua biển cát này, cái biển cát thường xuyên thay hình đổi dạng vì gió thổi nhưng vẫn mãi mãi là biển cát mà ông đã biết từ thuở nhỏ. Tuy vậy, tự đáy lòng mình, ông không thể không cảm thấy vui trước hạnh phúc của mỗi người lữ khách, sau bao ngày chỉ có cát vàng với trời xanh nay được thấy chà là xanh tươi hiện ra trước mắt. ‘Có thể Thượng đế tạo ra sa mạc chỉ để cho con người biết quý trọng cây chà là,’ ông nghĩ.”', N'Quest, Adventure fiction, Fantasy Fiction', 1, 1)
INSERT [dbo].[Book] ([id], [title], [author], [ISBN], [language], [description], [tag], [status], [idUser]) VALUES (5, N'Clean Code', N'Robert Cecil Martin', N'9780132350884', N'English', N'Even bad code can function. But if code isn’t clean, it can bring a development organization to its knees. Every year, countless hours and significant resources are lost because of poorly written code. But it doesn’t have to be that way.', N'Programing', 1, 1)
INSERT [dbo].[Book] ([id], [title], [author], [ISBN], [language], [description], [tag], [status], [idUser]) VALUES (6, N'Tony Buổi Sáng', N'Tony Buổi Sáng', N'5135272539721', N'Vietnamese', N'Có đôi khi vào những tháng năm bắt đầu vào đời, giữa vô vàn ngả rẽ và lời khuyên, khi rất nhiều dự định mà thiếu đôi phần định hướng, thì CẢM HỨNG là điều quan trọng để bạn trẻ bắt đầu bước chân đầu tiên trên con đường theo đuổi giấc mơ của mình. Cà Phê Cùng Tony là tập hợp những bài viết của tác giả Tony Buổi Sáng. Đúng như tên gọi, mỗi bài nhẹ nhàng như một tách cà phê, mà bạn trẻ có thể nhận ra một chút gì của chính mình hay bạn bè mình trong đó: Từ chuyện lớn như định vị bản thân giữa bạn bè quốc tế, cho đến chuyện nhỏ như nên chú ý những phép tắc xã giao thông thường.', N'Văn học', 1, 1)
INSERT [dbo].[Book] ([id], [title], [author], [ISBN], [language], [description], [tag], [status], [idUser]) VALUES (7, N'Tuổi trẻ đáng giá bao nhieu', N' Rosie Nguyen', N'9786045370193', N'Vietnamese', N'Vì sau tất cả, chẳng ai quan tâm.”

“Tôi đã đọc quyển sách này một cách thích thú. Có nhiều kiến thức và kinh nghiệm hữu ích, những điều mới mẻ ngay cả với người gần trung niên như tôi.

Tuổi trẻ đáng giá bao nhiêu? được tác giả chia làm 3 phần: HỌC, LÀM, ĐI.

Nhưng tôi thấy cuốn sách còn thể hiện một phần thứ tư nữa, đó là ĐỌC.

Hãy đọc sách, nếu bạn đọc sách một cách bền bỉ, sẽ đến lúc bạn bị thôi thúc không ngừng bởi ý muốn viết nên cuốn sách của riêng mình.', N'Văn học', 1, 1)
INSERT [dbo].[Book] ([id], [title], [author], [ISBN], [language], [description], [tag], [status], [idUser]) VALUES (8, N'The 7 Habits of Highly Effective People', N' Stephen Covey', N'5122812599574', N'English', N'The 7 Habits of Highly Effective People, first published in 1989, is a business and self-help book written by Stephen Covey.[1] Covey presents an approach to being effective in attaining goals by aligning oneself to what he calls "true north" principles based on a character ethic that he presents as universal and timeless. Covey defines effectiveness as the balance of obtaining desirable results with caring for that which produces those results. He illustrates this by referring to the fable of the goose that laid the golden eggs. He further claims that effectiveness can be expressed in terms of the P/PC ratio, where P refers to getting desired results and PC is caring for that which produces the results.', N'Self-help book, Non-fiction', 1, 4)
SET IDENTITY_INSERT [dbo].[Book] OFF
/****** Object:  Table [dbo].[Trading]    Script Date: 07/27/2018 08:01:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Trading](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idOwner] [int] NOT NULL,
	[idBorrower] [int] NULL,
	[idBook] [int] NOT NULL,
	[statusBook] [int] NOT NULL,
	[statusComplete] [bit] NOT NULL,
	[createDate] [date] NOT NULL,
	[completeDate] [date] NULL,
 CONSTRAINT [PK_Trading] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Trading] ON
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (1, 3, NULL, 3, 0, 0, CAST(0x7C3E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (2, 1, NULL, 4, 0, 0, CAST(0x813E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (3, 4, NULL, 4, 0, 0, CAST(0x813E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (4, 1, NULL, 5, 0, 0, CAST(0x813E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (5, 4, NULL, 5, 0, 0, CAST(0x813E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (6, 4, NULL, 3, 0, 0, CAST(0x823E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (7, 1, NULL, 3, 0, 0, CAST(0x823E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (8, 1, NULL, 3, 0, 0, CAST(0x823E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (9, 1, NULL, 3, 0, 0, CAST(0x823E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (10, 1, NULL, 6, 0, 0, CAST(0x823E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (11, 4, NULL, 6, 0, 0, CAST(0x823E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (12, 3, 1, 6, 1, 0, CAST(0x823E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (13, 1, 5, 7, 1, 0, CAST(0x833E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (14, 4, 1, 7, 3, 0, CAST(0x833E0B00 AS Date), NULL)
INSERT [dbo].[Trading] ([id], [idOwner], [idBorrower], [idBook], [statusBook], [statusComplete], [createDate], [completeDate]) VALUES (15, 4, 1, 8, 3, 0, CAST(0x843E0B00 AS Date), NULL)
SET IDENTITY_INSERT [dbo].[Trading] OFF
/****** Object:  Table [dbo].[ReviewBook]    Script Date: 07/27/2018 08:01:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ReviewBook](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idBook] [int] NOT NULL,
	[idUser] [int] NOT NULL,
	[content] [nvarchar](500) NULL,
	[createDate] [datetime] NOT NULL,
 CONSTRAINT [PK_ReviewBook] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[ReviewBook] ON
INSERT [dbo].[ReviewBook] ([id], [idBook], [idUser], [content], [createDate]) VALUES (60, 3, 1, N'Hay ! rat bo ich cho nguoi thieu ki nang giao tiep.', CAST(0x0000A92100000000 AS DateTime))
INSERT [dbo].[ReviewBook] ([id], [idBook], [idUser], [content], [createDate]) VALUES (61, 3, 1, N'test phát', CAST(0x0000A92400000000 AS DateTime))
INSERT [dbo].[ReviewBook] ([id], [idBook], [idUser], [content], [createDate]) VALUES (62, 3, 1, N'test animation', CAST(0x0000A92400000000 AS DateTime))
INSERT [dbo].[ReviewBook] ([id], [idBook], [idUser], [content], [createDate]) VALUES (63, 3, 4, N'cộng hòa
', CAST(0x0000A92700000000 AS DateTime))
INSERT [dbo].[ReviewBook] ([id], [idBook], [idUser], [content], [createDate]) VALUES (64, 7, 4, N'Sách rất hay
', CAST(0x0000A92800000000 AS DateTime))
SET IDENTITY_INSERT [dbo].[ReviewBook] OFF
/****** Object:  Table [dbo].[RateBook]    Script Date: 07/27/2018 08:01:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RateBook](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idBook] [int] NOT NULL,
	[idUser] [int] NOT NULL,
	[rate] [int] NOT NULL,
 CONSTRAINT [PK_RateBook] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[RateBook] ON
INSERT [dbo].[RateBook] ([id], [idBook], [idUser], [rate]) VALUES (1, 3, 1, 4)
INSERT [dbo].[RateBook] ([id], [idBook], [idUser], [rate]) VALUES (2, 4, 4, 4)
INSERT [dbo].[RateBook] ([id], [idBook], [idUser], [rate]) VALUES (3, 4, 1, 1)
INSERT [dbo].[RateBook] ([id], [idBook], [idUser], [rate]) VALUES (4, 3, 4, 1)
INSERT [dbo].[RateBook] ([id], [idBook], [idUser], [rate]) VALUES (5, 7, 4, 4)
INSERT [dbo].[RateBook] ([id], [idBook], [idUser], [rate]) VALUES (6, 8, 1, 4)
SET IDENTITY_INSERT [dbo].[RateBook] OFF
/****** Object:  Table [dbo].[ImageBook]    Script Date: 07/27/2018 08:01:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImageBook](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[urlImage] [nvarchar](200) NOT NULL,
	[idBook] [int] NOT NULL,
 CONSTRAINT [PK_ImageBook] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[ImageBook] ON
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (1, N'3_cover1.jpg', 3)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (2, N'4_cover1.jpg', 4)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (3, N'CoverBook.jpg', 4)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (4, N'CoverBook.jpg', 4)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (5, N'CoverBook.jpg', 4)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (6, N'CoverBook.jpg', 4)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (7, N'CoverBook.jpg', 3)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (8, N'CoverBook.jpg', 3)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (9, N'CoverBook.jpg', 3)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (10, N'CoverBook.jpg', 3)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (11, N'5_cover1.png', 5)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (12, N'CoverBook.jpg', 5)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (13, N'CoverBook.jpg', 5)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (14, N'CoverBook.jpg', 5)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (15, N'CoverBook.jpg', 5)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (16, N'6_cover1.png', 6)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (17, N'CoverBook.jpg', 6)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (18, N'CoverBook.jpg', 6)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (19, N'CoverBook.jpg', 6)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (20, N'CoverBook.jpg', 6)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (21, N'7_cover1.jpg', 7)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (22, N'CoverBook.jpg', 7)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (23, N'CoverBook.jpg', 7)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (24, N'CoverBook.jpg', 7)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (25, N'CoverBook.jpg', 7)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (26, N'8_cover1.jpg', 8)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (27, N'8_cover2.jpg', 8)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (28, N'CoverBook.jpg', 8)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (29, N'CoverBook.jpg', 8)
INSERT [dbo].[ImageBook] ([id], [urlImage], [idBook]) VALUES (30, N'CoverBook.jpg', 8)
SET IDENTITY_INSERT [dbo].[ImageBook] OFF
/****** Object:  Default [DF_Book_status]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[Book] ADD  CONSTRAINT [DF_Book_status]  DEFAULT ((1)) FOR [status]
GO
/****** Object:  Default [DF_RateBook_rate]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[RateBook] ADD  CONSTRAINT [DF_RateBook_rate]  DEFAULT ((10)) FOR [rate]
GO
/****** Object:  Default [DF_Trading_statusBook]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[Trading] ADD  CONSTRAINT [DF_Trading_statusBook]  DEFAULT ((0)) FOR [statusBook]
GO
/****** Object:  Default [DF_Trading_statusComplete]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[Trading] ADD  CONSTRAINT [DF_Trading_statusComplete]  DEFAULT ((0)) FOR [statusComplete]
GO
/****** Object:  Default [DF_User_avatar]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [DF_User_avatar]  DEFAULT (N'default.jpg') FOR [avatar]
GO
/****** Object:  Default [DF_User_userPoint]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [DF_User_userPoint]  DEFAULT ((10)) FOR [userPoint]
GO
/****** Object:  ForeignKey [FK_Book_User]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[Book]  WITH CHECK ADD  CONSTRAINT [FK_Book_User] FOREIGN KEY([idUser])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[Book] CHECK CONSTRAINT [FK_Book_User]
GO
/****** Object:  ForeignKey [FK_ImageBook_Book]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[ImageBook]  WITH CHECK ADD  CONSTRAINT [FK_ImageBook_Book] FOREIGN KEY([idBook])
REFERENCES [dbo].[Book] ([id])
GO
ALTER TABLE [dbo].[ImageBook] CHECK CONSTRAINT [FK_ImageBook_Book]
GO
/****** Object:  ForeignKey [FK_RateBook_Book]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[RateBook]  WITH CHECK ADD  CONSTRAINT [FK_RateBook_Book] FOREIGN KEY([idBook])
REFERENCES [dbo].[Book] ([id])
GO
ALTER TABLE [dbo].[RateBook] CHECK CONSTRAINT [FK_RateBook_Book]
GO
/****** Object:  ForeignKey [FK_RateBook_User]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[RateBook]  WITH CHECK ADD  CONSTRAINT [FK_RateBook_User] FOREIGN KEY([idUser])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[RateBook] CHECK CONSTRAINT [FK_RateBook_User]
GO
/****** Object:  ForeignKey [FK_ReviewBook_Book]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[ReviewBook]  WITH CHECK ADD  CONSTRAINT [FK_ReviewBook_Book] FOREIGN KEY([idBook])
REFERENCES [dbo].[Book] ([id])
GO
ALTER TABLE [dbo].[ReviewBook] CHECK CONSTRAINT [FK_ReviewBook_Book]
GO
/****** Object:  ForeignKey [FK_ReviewBook_User]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[ReviewBook]  WITH CHECK ADD  CONSTRAINT [FK_ReviewBook_User] FOREIGN KEY([idUser])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[ReviewBook] CHECK CONSTRAINT [FK_ReviewBook_User]
GO
/****** Object:  ForeignKey [FK_Trading_Book]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[Trading]  WITH CHECK ADD  CONSTRAINT [FK_Trading_Book] FOREIGN KEY([idBook])
REFERENCES [dbo].[Book] ([id])
GO
ALTER TABLE [dbo].[Trading] CHECK CONSTRAINT [FK_Trading_Book]
GO
/****** Object:  ForeignKey [FK_Trading_User]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[Trading]  WITH CHECK ADD  CONSTRAINT [FK_Trading_User] FOREIGN KEY([idOwner])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[Trading] CHECK CONSTRAINT [FK_Trading_User]
GO
/****** Object:  ForeignKey [FK_Trading_User1]    Script Date: 07/27/2018 08:01:47 ******/
ALTER TABLE [dbo].[Trading]  WITH CHECK ADD  CONSTRAINT [FK_Trading_User1] FOREIGN KEY([idBorrower])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[Trading] CHECK CONSTRAINT [FK_Trading_User1]
GO
