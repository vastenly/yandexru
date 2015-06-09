package com.firstutility.taf.connect.ssh;

import java.io.IOException;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Shell;
import net.schmizz.sshj.transport.TransportException;
import net.sf.expectit.Expect;

import org.apache.log4j.Logger;

import com.firstutility.taf.utils.exec.Command;

public class SshConnector {
	
	private static final Logger log = Logger.getLogger(SshConnector.class);
	
	private SSHClient sshClient;
	private Session session;
	private Shell shell;
	private Expect expect;
	
	/**
	 * @param userHomePath * required, not null
	 * @param remoteHostUsername * required, not null
	 * @param remoteHostName * required, not null
	 * @param remoteHostPort * set null, if port not defined
	 * @param remoteHostFingerprint * required, not null
	 * @return initialized SSH client
	 */
	public SshConnection initConnection(String userHomePath, String remoteHostUsername, String remoteHostName, Integer remoteHostPort, String remoteHostFingerprint) {

		log.info("[SshConnector] Init SSH connection.");
		sshClient = new SSHClient();
		
		try {
			System.setProperty("user.home", userHomePath);
			
			sshClient.addHostKeyVerifier(remoteHostFingerprint);
			sshClient.loadKnownHosts();
			if (remoteHostPort != null) sshClient.connect(remoteHostName, remoteHostPort);
			else sshClient.connect(remoteHostName);
			sshClient.authPublickey(remoteHostUsername);
			
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return new SshConnection();
	}
	
	public class SshConnection {
		
		private Session startSession() {
			log.info("[SshConnector] Init new SSH session.");
			try {
				session = sshClient.startSession();
			} catch (ConnectionException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			} catch (TransportException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
			return session;
		}
		
		/**
		 * @return initialized SSH connection
		 */
		public SshConnected connect() {
			startSession();
			return new SshConnected();
		}
		
		public void close() {
			SshConnector.this.close();
		}
	}
	
	public class SshConnected {
		
		private Session.Command cmd;
		
		public SshConnected execCommand(String command) {
			log.info("[SshConnector] Execute [" +command+ "] shell command thru SSH.");
			try {
				cmd = session.exec(command);
			} catch (ConnectionException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			} catch (TransportException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
			return this;
		}
		
		public SshConnected execCommand(Command command) {
			log.info("[SshConnector] Execute [" +command.getCommand()+ "] shell command thru SSH.");
			try {
				cmd = session.exec(command.getCommand());
			} catch (ConnectionException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			} catch (TransportException e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
			return this;
		}
		
		public String getResult() {
			String result = null;
			try {
				result = IOUtils.readFully(cmd.getInputStream()).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	log.info("[SshConnector] Command execution result: \n" + result);
			return result;
		}
		
		public void close() {
			SshConnector.this.close();
		}
	}
	
	/**
	 * Close SSH connection 
	 */
	public void close() {
		try {
			if (expect != null) {
				expect.close();
			}
			if (shell != null) {
				shell.close();
			}
			if (session != null) {
				session.close();
			}
			if (sshClient != null) {
				sshClient.close();
			}
		} catch (TransportException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (ConnectionException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

}
