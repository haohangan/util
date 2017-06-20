package simple.cglib.minix;

import org.springframework.cglib.proxy.Mixin;

public class TestMixin {
    public static void main(String[] args) {
    	Mixin mixin = Mixin.create(new Class[]{Ipv4.class,Ipv6.class,IpvX.class}, new Object[]{new InterIpv4(),new InterIpv6()});
    	IpvX ipvx = (IpvX)mixin;
    	System.out.println(ipvx.getIpv4());
    	System.out.println(ipvx.getIpv6());
	}
}
