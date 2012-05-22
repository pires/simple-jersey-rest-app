/*
 * TODO put header
 */
package rest;

/**
 * Definition of our Adminitration Service.
 */
public interface IAdminService {

	public ISP[] getISPs();

	public ISP getISP(Long id);

	public void addISP(ISP isp);

	public void removeISP(Long id);
}
