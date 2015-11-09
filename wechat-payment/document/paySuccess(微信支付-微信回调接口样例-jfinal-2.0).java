class  
{
	public void paySuccess() {
		System.out.println("ͳһ֧���ص�������ʼ...");

		CallbackService callback = new CallbackServiceImpl(SimpleEntityUtil.getInstance());

		Map<String, String> resultMap = null;
		NotificationResult noti = null;
		try {
			InputStream in = getRequest().getInputStream();
			String resultXml = inputStreamToString(in);
			log.info("\n======WeChatPay ɨ��֧���ɹ��ص����� ��ʼ======\n" + resultXml);
			Notification result = callback.recveiveNotification(resultXml);
			String notiresult = result.getResult().toXml();
			
			System.out.println(resultMap);
			noti = NotificationResult.successResult();
		} catch (IOException e) {
			noti = NotificationResult.nullResult();
			e.printStackTrace();
		}
		System.out.println("ͳһ֧���ص���������...");

		String result = noti.toXml();
		renderText(result);//����΢��֧���ص����
		renderJson("�ص���ɣ������Ѿ���ӡ");
	}


	/**
	 * ������ת�ַ���
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public String inputStreamToString(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	/**
	 * �����ص�����������ݲο�����
	 * 
	 * @param text
	 *            ��������
	 */

	public void render() {
		HttpServletResponse response;
		PrintWriter writer = null;
		try {
			response.setHeader("Pragma", "no-cache"); // HTTP/1.0 caches might
														// not implement
														// Cache-Control and
														// might only implement
														// Pragma: no-cache
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			if (contentType == null) {
				response.setContentType(DEFAULT_CONTENT_TYPE);
			} else {
				response.setContentType(contentType);
				response.setCharacterEncoding(getEncoding());
			}

			writer = response.getWriter();
			writer.write(text);
			writer.flush();
		} catch (IOException e) {
			throw new RenderException(e);
		} finally {
			if (writer != null)
				writer.close();
		}
	}


}
