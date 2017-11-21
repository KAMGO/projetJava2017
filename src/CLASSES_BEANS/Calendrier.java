package CLASSES_BEANS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Calendrier {
	private Set<Balade> listBalade = new HashSet<Balade>();
	public Calendrier() {}
	public Calendrier(Set<Balade> listBalade) {
		this.listBalade = listBalade;
	}

	public Set<Balade> getListBalade() {
		return listBalade;
	}
	public void addBalade(Balade bal) {
	    if(!listBalade.contains(bal))
	    	listBalade.add(bal);
	   }
	public void removeBalade(Balade bal) {
		    this.listBalade.remove(bal);
	}
	public void setListBalade(Set<Balade> listBalade) {
		this.listBalade = listBalade;
	}
	public void afficheCalandrier(){
		System.out.println(" NUMERO BALADE \t LIEU DE LA BALADE \t  DATE DE LA BALADE  \t CATEGORIE ");
		System.out.println();
		for(Balade balade : this.listBalade){
			Date date1 = new Date();
			Date date2 = new Date();
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
		    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		    try {
				date1=format1.parse(balade.getDate());
				calendar1.setTime(date1);
				calendar2.setTime(date2);
				
			} catch (ParseException e) {
				System.out.println(" probleme avec la date "+e);
			}
		    if(calendar1.after(calendar2)){
		    	if(balade.getIdCat()==1){
			    	System.out.print(balade.getId()+"\t\t"+balade.getLieuBalade()+"\t\t"+balade.getDate()+"\t\t Cyclo(Velo sur route )");
			    	System.out.println();
		    	}
		    	if(balade.getIdCat()==2){
			    	System.out.print(balade.getId()+"\t\t"+balade.getLieuBalade()+"\t\t"+balade.getDate()+"\t\t VTT descendeurs");
			    	System.out.println();
		    	}
		    	if(balade.getIdCat()==3){
			    	System.out.print(balade.getId()+"\t\t"+balade.getLieuBalade()+"\t\t"+balade.getDate()+"\t\t VTT Randonneurs");
			    	System.out.println();
		    	}
		    	if(balade.getIdCat()==4){
			    	System.out.print(balade.getId()+"\t\t"+balade.getLieuBalade()+"\t\t"+balade.getDate()+"\t\t VTT Trialistes");
			    	System.out.println();
		    	}
		    }
		}
	}
	
}
