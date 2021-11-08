package common;

public class ModelAndView {
	
	// String view : 응답 View
	// boolean isRedirect : true(Redirect) / false(forward) redirect or forward
	private String view;
	private boolean isRedirect;		// Redirect인가.
	
	// bean : data 저장해서 옮기는 역할.		원래 모델의 역할
	
	public ModelAndView() {
		
	}

	public ModelAndView(String view, boolean isRedirect) {
		super();
		this.view = view;
		this.isRedirect = isRedirect;
	}
	
	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	

}
